import java.io.*;
import java.util.Arrays;


public class Main {

    public static void showInfo(String fileName){
        File f = new File(fileName);

        if(f.exists()){
            System.out.println("Path: " + f.getPath());
            if(f.isFile()){
                System.out.println("File size: " + f.length());
                System.out.println("Last modified date: " + f.lastModified());
            }else if(f.isDirectory()){
                System.out.println("Directory Listing: " + Arrays.toString(f.list()));
            }
        }else{
            System.out.println("File does not exist");
        }
    }

    public static void readFile(String fileName){
        try{
            // read bytes
            FileInputStream f1 = new FileInputStream(fileName);
            System.out.println("Available : " + f1.available());
            while (f1.available() > 0){
                System.out.println((char) f1.read());
            }
            f1.close();

            // read bytes array
            FileInputStream f2 = new FileInputStream(fileName);
            int available = f2.available();
            System.out.println("Available : " + available);
            byte b[] = new byte[available];
            f2.read(b);
            System.out.println(new String(b, 0, available));
            f2.close();

        }catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }catch (IOException e){
            System.out.println("Some I/O error");
        }
    }


    public static void writeFile(String fileName){
        try{
            byte[] bytesToWrite = {1, 2, 3};

            FileOutputStream f1 = new FileOutputStream(fileName);
            f1.write(bytesToWrite);
            f1.close();
            System.out.println("Bytes written: " + bytesToWrite.length);

        }catch (IOException e){
            System.out.println("Some I/O error");
        }
    }

    public static void bufferedIO(String fileName){
        try {
            InputStream inStream = null;
            OutputStream outStream = null;

            //Записать в файл некоторое количество байт
            long timeStart = System.currentTimeMillis();
            outStream = new FileOutputStream(fileName);
            outStream = new BufferedOutputStream(outStream);
            for(int i=1000000; --i>=0;){
                outStream.write(i);
            }
            long time = System.currentTimeMillis() - timeStart;
            System.out.println("Writing time: " + time + " ms");
            outStream.close();

            // Определить время считывания без буферизации
            timeStart = System.currentTimeMillis();
            inStream = new FileInputStream(fileName);
            while(inStream.read()!=-1){
            }
            time = System.currentTimeMillis() - timeStart;
            inStream.close();
            System.out.println("Direct read time: " + (time) + " ms");

            timeStart = System.currentTimeMillis();
            inStream = new FileInputStream(fileName);
            inStream = new BufferedInputStream(inStream);
            while(inStream.read()!=-1){
            }
            time = System.currentTimeMillis() - timeStart;
            inStream.close();
            System.out.println("Buffered read time: " + (time) + " ms");
        } catch (IOException e) {
            System.out.println("IOException: " + e.toString());
        }
    }

    public static void fileReaderDemo(String fileName){
        try{
            FileReader f1 = new FileReader(fileName);
            System.out.println("Direct file reader:");

            int c;
            while ((c = f1.read()) != -1){
                System.out.print((char) c);
            }
            f1.close();

            System.out.println("\nBuffered file reader:");
            FileReader f2 = new FileReader(fileName);
            BufferedReader f = new BufferedReader(f2);

            while ((c = f.read()) != -1){
                System.out.print((char) c);
            }
            f2.close();

        }catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }catch (IOException e){
            System.out.println("Some I/O error");
        }
    }

    public static void fileWriterDemo(String fileName, String input){
        try{
            char buffer[] = new char[input.length()];
            input.getChars(0, input.length(), buffer, 0);

            FileWriter f1 = new FileWriter(fileName, true);

            for(char item : buffer ){
                f1.append(item);
            }
            f1.close();
        }catch (IOException e){
            System.out.println("Some I/O error");
        }
    }

    public static void main(String[] args) {
        //showInfo("D:/Test");
        //showInfo("D:/Test/example.txt");
        //readFile("D:/Test");
        //readFile("D:/Test/example.txt");
        //writeFile("D:/Test/example2");
        //bufferedIO("D:/Test/m");
        //fileReaderDemo("D:/Test/example.txt");
        //fileWriterDemo("D:/Test/examplem.txt", "Hello World!\n");
    }

}