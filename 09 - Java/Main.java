import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
enum Direction  {
    UP (38)  {
        public Direction opposite() { return DOWN; }
    },
    DOWN (40) {
        public Direction opposite() { return UP; }
    },
    RIGHT (39) {
        public Direction opposite() { return LEFT; }
    },
    LEFT (37) {
        public Direction opposite() { return RIGHT; }
    };

    int keyboardNumber;

    Direction(int keyboardNumber) { this.keyboardNumber = keyboardNumber; }

    public abstract Direction opposite();
}

public class Main {

    /*
    Simple enum example
     */
    static void enumDay(Day currentDay){
        if(currentDay == Day.SUNDAY){
            System.out.println("I don't like Sunday");
        } else if(currentDay == Day.MONDAY){
            System.out.println("I like it!");
        } else{
            System.out.println("OK!");
        }
    }

    /*
    Enum example with methods
     */
    static void enumDirection(Direction dir){
        System.out.println("Current direction is " + dir);
        System.out.println("Key number is " + dir.keyboardNumber);
        System.out.println("Opposite direction is " + dir.opposite());
    }

    /*
    * StringTokenizer and split
     */
    static void tokenizing (String sourceString, String delimiter){

        // StringTokenizer
        StringTokenizer st = new StringTokenizer(sourceString, delimiter);
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }

        // Split method of String Class
        String[] result = sourceString.split(delimiter);
        for (int x=0; x<result.length; x++){
            System.out.println(result[x]);
        }

    }

    /*
    Hash functions
     */
    static String getMD5(String inputString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // DSA, RSA, MD5, SHA-1, SHA-256
            byte[] messageDigest = md.digest(inputString.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        enumDay(Day.MONDAY);
        enumDirection(Direction.LEFT);
        tokenizing("My test string.", "t");
        System.out.println(getMD5("ITIS"));
    }
}
