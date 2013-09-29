import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class Main {

    // Сериализация
    public static void Serialize(Object object, String fileName){
        try{
            FileOutputStream file = new FileOutputStream(fileName); // Файловый поток
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(object);
            output.flush();
            output.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Десериализация
    public static Object Deserialize(String fileName){
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream object = new ObjectInputStream(file);

            return object.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Пример использования буферов и каналов для записи/чтения
    public static void nioReadWrite (String outString, String fileName){
        try{
            // Writing
            FileOutputStream outputFile = new FileOutputStream (fileName);
            FileChannel outFileChannel = outputFile.getChannel();
            ByteBuffer outBuffer = ByteBuffer.wrap(outString.getBytes()); // создаем буффер
            outFileChannel.write(outBuffer);
            outFileChannel.close();

            //Reading
            FileInputStream inpFile = new FileInputStream(fileName);
            FileChannel inpFileChannel = inpFile.getChannel(); // создаем канал
            ByteBuffer inpBuffer = ByteBuffer.allocate(1024); // создаем буффер
            inpFileChannel.read(inpBuffer);
            inpBuffer.flip();
            while (inpBuffer.hasRemaining()){
                System.out.print((char) inpBuffer.get());
            }
            inpFileChannel.close();

            // Reading/Writing
            RandomAccessFile rwFile = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = rwFile.getChannel(); // создаем канал
            fileChannel.position(fileChannel.size());
            ByteBuffer buffer = ByteBuffer.allocate(1024); // создаем буффер
            fileChannel.read(buffer);
            fileChannel.write(ByteBuffer.wrap("\nSome Text".getBytes("UTF-8")));
            fileChannel.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Копирование файла
    public static void fileCopy(String fileName, String toFilename){
        try{
            FileInputStream inpFile = new FileInputStream(fileName);
            FileChannel inpFileChannel = inpFile.getChannel();

            FileOutputStream outputFile = new FileOutputStream (toFilename);
            FileChannel outFileChannel = outputFile.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1); // создаем буффер
            while(inpFileChannel.read(buffer) != -1){
                buffer.flip();
                outFileChannel.write(buffer);
                buffer.clear();
            }

            outFileChannel.close();
            inpFileChannel.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Содаем класс
        //Timer timer = new Timer();
        //timer.getTimer();
        // Сериализуем в файл
        //Serialize(timer, "D:/Test/temp.out");

        // Десериализуем
        //Timer timer2 = (Timer) Deserialize("D:/Test/temp.out");
        //timer2.getTimer();

        // Запись/чтение с помощью NIO
        //nioReadWrite("Hello, World!", "D:/Test/nio.txt");

        // Копирование файла с помощью каналов и буфера
        //fileCopy("D:/Test/example.txt", "D:/Test/out.txt");
    }
}