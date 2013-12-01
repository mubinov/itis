import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

public class XMLClassBinding {
    public static void main(String[] args) {
        writeXML();
        readXML();
    }
    private static void readXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(Robot.class);
            Unmarshaller u = context.createUnmarshaller();
            Robot robot = (Robot)u.unmarshal( new File("robot.xml"));
            System.out.println("Robot name: " + robot.getName());
            System.out.println("Robot surname: " + robot.getSurname());
            System.out.println("Robot is bot: " + robot.getBotFlag());

        }catch (JAXBException e) {
            System.out.println("JAXB-исключения");
            e.printStackTrace();
        }
    }
    private static void writeXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(Robot.class);
            Marshaller m = context.createMarshaller();

            Robot s = new Robot(1, "Bender", "Rodriguez");//объект
            m.marshal(s, new FileOutputStream("robot.xml"));

        } catch (FileNotFoundException e) {
            System.out.println("XMl-файл не найден");
            e.printStackTrace();
        } catch (JAXBException e) {
            System.out.println("JAXB-исключения");
            e.printStackTrace();
        }
    }
    @XmlRootElement
    private static class Robot {//внутренний класс
        private int id;
        private String name;
        private String surname;
        private boolean botFlag;

        public Robot(){
        }
        public Robot(int id, String name, String surname) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            botFlag = true;
        }
        public int getID() {
            return id;
        }
        public String getName() {
            return name;
        }
        public boolean getBotFlag() {
            return botFlag;
        }
        public String getSurname(){
            return surname;
        }
        public void setID(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }
        public void setBotFlag(boolean botFlag){
            this.botFlag = botFlag;
        }
    }
}