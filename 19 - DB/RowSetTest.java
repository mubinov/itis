import com.sun.rowset.*;
import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.sql.rowset.*;

public class RowSetTest
{
    public static void main(String[] args)
    {
        JFrame frame = new RowSetFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class RowSetFrame extends JFrame
{
    public RowSetFrame()
    {
        setTitle("RowSetTest");
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
            Connection conn = getConnection();
            try
            {
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet mrs = meta.getTables(null, null, null,
                        new String[] { "TABLE" });
                while (mrs.next())
                    tableNames.addItem(mrs.getString(3));
            }
            finally
            {
                conn.close();
            }
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
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new
                                               ActionListener()
                                               {
                                                   public void actionPerformed(ActionEvent event)
                                                   {
                                                       deleteRow();
                                                   }
                                               });
        buttonPanel.add(deleteButton);
        saveButton = new JButton("Save");
        saveButton.addActionListener(new
                                             ActionListener()
                                             {
                                                 public void actionPerformed(ActionEvent event)
                                                 {
                                                     saveChanges();
                                                 }
                                             });
        buttonPanel.add(saveButton);
    }

    public void showTable(String tableName)
    {
        try
        {
            Connection conn = getConnection();
            try
            {
                Statement stat = conn.createStatement();
                ResultSet result =
                        stat.executeQuery("SELECT * FROM " + tableName);
                rs = new CachedRowSetImpl();
                rs.setTableName(tableName);
                rs.populate(result);
            }
            finally
            {
                conn.close();
            }
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
        catch (IOException e)
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
            System.out.println("Error " + e);
        }
    }

    public void showNextRow()
    {
        try
        {
            if (rs == null || rs.isLast()) return;
            rs.next();
            dataPanel.showRow(rs);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void deleteRow()
    {
        try
        {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
            try
            {
                rs.deleteRow();
                rs.acceptChanges(conn);
            }
            finally
            {
                conn.commit();
                conn.close();
            }

            if (!rs.isLast()) rs.next();
            else if (!rs.isFirst()) rs.previous();
            else rs = null;
            dataPanel.showRow(rs);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void saveChanges()
    {
        try
        {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
            try
            {
                ArrayList<JTextField> fields = dataPanel.getFields();
                for (int i = 0; i < fields.size(); i++)
                {
                    rs.updateString(fields.get(i).getName(), fields.get(i).getText());
                }
                rs.updateRow();
                rs.acceptChanges(conn);
            }
            finally
            {
                conn.commit();
                conn.close();
                dataPanel.showRow(rs);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
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
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;
    private JButton previousButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton saveButton;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox tableNames;
    private CachedRowSet rs;
}