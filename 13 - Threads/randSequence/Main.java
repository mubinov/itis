package randSequence;

public class Main {

    public static void main(String[] args) {
	    RandSequence rand = new RandSequence();
        rand.start();
        try{
            rand.join();
        } catch (InterruptedException e){
        }

        System.out.println(rand.outString);
    }
}
