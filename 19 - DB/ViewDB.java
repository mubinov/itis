import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ViewDB
{
    public static void main(String[] args)
    {
        JFrame frame = new ViewDBFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class ViewDBFrame extends JFrame
{
    public ViewDBFrame()
    {
        setTitle("ViewDB");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        tableNames = new JComboBox();
        tableNames.addActionListener(new
                                             ActionListener()
                                             {
                                                 public void actionPerformed(ActionEvent event)
                                                 {
                                                     showTable((String) tableNames.getSelectedItem());
                                                 }
                                             });
        add(tableNames, BorderLayout.NORTH);
        try
        {
            conn = getConnection();
            meta = conn.getMetaData();
            createStatement();
            getTableNames();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        if (scrolling)
        {
            previousButton = new JButton("Previous");
            previousButton.addActionListener(new
                                                     ActionListener()
                                                     {
                                                         public void actionPerformed(ActionEvent event)
                                                         {
                                                             showPreviousRow();
                                                         }
                                                     });
            buttonPanel.add(previousButton);
        }
        nextButton = new JButton("Next");
        nextButton.addActionListener(new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                showNextRow();
            }
        });
        buttonPanel.add(nextButton);
        addWindowListener(new
                                  WindowAdapter()
                                  {
                                      public void windowClosing(WindowEvent event)
                                      {
                                          try
                                          {
                                              if (conn != null) conn.close();
                                          }
                                          catch (SQLException e)
                                          {
                                              while (e != null)
                                              {
                                                  e.printStackTrace();
                                                  e = e.getNextException();
                                              }
                                          }
                                      }
                                  });
    }

    public void createStatement() throws SQLException
    {
        if (meta.supportsResultSetType(
                ResultSet.TYPE_SCROLL_INSENSITIVE))
        {
            stat = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            scrolling = true;
        }
        else
        {
            stat = conn.createStatement();
            scrolling = false;
        }
    }

    public void getTableNames() throws SQLException
    {
        ResultSet mrs = meta.getTables(null, null, null,
                new String[] { "TABLE" });
        while (mrs.next())
            tableNames.addItem(mrs.getString(3));
        mrs.close();
    }

    public void showTable(String tableName)
    {
        try
        {
            if (rs != null) rs.close();
            rs = stat.executeQuery("SELECT * FROM " + tableName);
            if (scrollPane != null)
                remove(scrollPane);
            dataPanel = new DataPanel(rs);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane, BorderLayout.CENTER);
            validate();
            showNextRow();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void showPreviousRow()
    {
        try
        {
            if (rs == null || rs.isFirst()) return;
            rs.previous();
            dataPanel.showRow(rs);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void showNextRow()
    {
        try
        {
            if (rs == null || scrolling && rs.isLast()) return;
            if (!rs.next() && !scrolling)
            {
                rs.close();
                rs = null;
                return;
            }
            dataPanel.showRow(rs);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public static Connection getConnection()
            throws SQLException, IOException
    {
        Properties props = new Properties();
        FileInputStream in
                = new FileInputStream("database.properties");
        props.load(in);
        in.close();
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
            System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private JButton previousButton;
    private JButton nextButton;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox tableNames;
    private Connection conn;
    private Statement stat;
    private DatabaseMetaData meta;
    private ResultSet rs;
    private boolean scrolling;
}

class DataPanel extends JPanel
{
    public DataPanel(ResultSet rs) throws SQLException
    {
        fields = new ArrayList<JTextField>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++)
        {
            gbc.gridy = i - 1;
            String columnName = rsmd.getColumnLabel(i);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(columnName), gbc);
            int columnWidth = rsmd.getColumnDisplaySize(i);
            JTextField tb = new JTextField(columnWidth);
            tb.setName(columnName);
            fields.add(tb);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(tb, gbc);
        }
    }

    public void showRow(ResultSet rs) throws SQLException
    {
        for (int i = 1; i <= fields.size(); i++)
        {
            String field = rs.getString(i);
            JTextField tb = (JTextField) fields.get(i - 1);
            tb.setText(field);
        }
    }
    public  ArrayList<JTextField> getFields(){
        return fields;
    }
    private ArrayList<JTextField> fields;
}