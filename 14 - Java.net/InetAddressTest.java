import java.net.*;

class InetAddressTest
{
    public static void main(String args[]) throws UnknownHostException {
        // Local host info
        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);

        // WebSite info
        try{
            Address = InetAddress.getByName("mubinov.com");
            System.out.println(Address);
        }catch(UnknownHostException e){
            System.out.println("Host not found");
        }

        // Fake WebSite info
        try{
            Address = InetAddress.getByName("host-not-found.com");
            System.out.println(Address);
        }catch(UnknownHostException e){
            System.out.println("Host not found");
        }

        // All ip by Site
        try{
            InetAddress SW[] = InetAddress.getAllByName("google.com");
            for (int i=0; i<SW.length; i++)
                System.out.println(SW[i]);
        }catch(UnknownHostException e){
            System.out.println("Host not found");
        }
    }
}