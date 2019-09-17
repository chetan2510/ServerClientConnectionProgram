import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;


    public Client(String address, int port) throws IOException {

        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            dis = new DataInputStream(System.in);

            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            System.out.println("Caught an exception"+ex);
        }

        String line = "";

        while(!line.equals("over")) {

            try
            {
                line = dis.readLine();
                dos.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            dis.close();
            dos.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 5000);
    }
}
