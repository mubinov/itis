import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLTest {

    private static final String SPACE = "   ";
    /**
     * Возвращает объект Document, который является объектным представлением
     * XML документа.
     */
    private static Document getDocument() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.parse(new File("app.xml"));
        } catch (Exception exception) {
            String message = "XML parsing error!";
            throw new Exception(message);
        }
    }

    private static void showDocument(Document doc) {
        StringBuffer content = new StringBuffer();
        Node node = doc.getChildNodes().item(0);
        ApplicationNode appNode = new ApplicationNode(node);

        content.append("Application \n");

        List<ClassNode> classes = appNode.getClasses();

        for (int i = 0; i < classes.size(); i++) {
            ClassNode classNode = classes.get(i);
            content.append(SPACE + "Class: " + classNode.getName() + " \n");

            List<MethodNode> methods = classNode.getMethods();

            for (int j = 0; j < methods.size(); j++) {
                MethodNode methodNode = methods.get(j);
                content.append(SPACE + SPACE + "Method: "
                        + methodNode.getName() + " \n");
            }
        }

        System.out.println(content.toString());
    }

    public static void main(String[] args) {
        try{
            Document doc = getDocument();
            showDocument(doc);
        }catch(Exception e)  {

        }
    }

    /**
     * Объектное представление приложения.
     */
    public static class ApplicationNode {

        Node node;

        public ApplicationNode(Node node) {
            this.node = node;
        }

        public List<ClassNode> getClasses() {
            ArrayList<ClassNode> classes = new ArrayList<ClassNode>();

            /**
             * Получаем список дочерних узлов для данного узла XML, который
             * соответствует приложению application. Здесь будут располагаться
             * все узлы Node, каждый из которых является объектным
             * представлением тега class для текущего тега application.
             */
            NodeList classNodes = node.getChildNodes();

            for (int i = 0; i < classNodes.getLength(); i++) {
                Node node = classNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    /**
                     * Создаем на основе Node узла своё объектное
                     * представление класса.
                     */
                    ClassNode classNode = new ClassNode(node);
                    classes.add(classNode);
                }
            }

            return classes;
        }

    }

    /**
     * Объектное представление класса.
     */
    public static class ClassNode {

        Node node;

        /**
         * Создаем новый экземпляр объекта на основе Node узла.
         */
        public ClassNode(Node node) {
            this.node = node;
        }

        /**
         * Возвращает список методов класса.
         */
        public List<MethodNode> getMethods() {
            ArrayList<MethodNode> methods = new ArrayList<MethodNode>();

            /**
             * Получаем список дочерних узлов для данного узла XML, 
             * который соответствует классу class. Здесь будут располагаться 
             * все узлы Node, каждый из которых является объектным 
             * представлением тега method для текущего тега class.
             */
            NodeList methodNodes = node.getChildNodes();

            for (int i = 0; i < methodNodes.getLength(); i++) {
                node = methodNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    /**
                     * Создаем на основе Node узла своё объектное представление
                     * метода.
                     */
                    MethodNode methodNode = new MethodNode(node);
                    methods.add(methodNode);
                }
            }

            return methods;
        }

        /**
         * Возвращае имя класса.
         */
        public String getName() {

            /**
             * Получаем атрибуты узла метода.
             */
            NamedNodeMap attributes = node.getAttributes();

            /**
             * Получаем узел аттрибута.
             */
            Node nameAttrib = attributes.getNamedItem("name");

            /**
             * Возвращаем значение атрибута.
             */
            return nameAttrib.getNodeValue();
        }
    }

    /**
     * Объектное представление сущности метод класса.
     */
    public static class MethodNode {

        Node node;

        /**
         * Создаем новый экземпляр объекта на основе Node узла.
         */
        public MethodNode(Node node) {
            this.node = node;
        }

        /**
         * Возвращает имя метода.
         */
        public String getName() {

            /**
             * Получаем атрибуты узла метода.
             */
            NamedNodeMap attributes = node.getAttributes();

            /**
             * Получаем узел аттрибута.
             */
            Node nameAttrib = attributes.getNamedItem("name");

            /**
             * Возвращаем значение атрибута.
             */
            return nameAttrib.getNodeValue();
        }

    }

}