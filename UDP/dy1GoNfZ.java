package UDP;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import UDP.Product;



public class dy1GoNfZ {
    
    public static void main(String[] args) throws Exception {
        String serverHost = "203.162.10.109";
        int serverPort = 2209;
        String studentCode = "B21DCCN562";
        String qCode = "dy1GoNfZ";

        try {
            InetAddress server = InetAddress.getByName(serverHost);
            DatagramSocket socket = new DatagramSocket();
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendBuffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, server, serverPort);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[2048];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String res = new String(receivePacket.getData(), 0, 8);
            System.out.println("Received from server: " + res);

            ByteArrayInputStream bis = new ByteArrayInputStream(receivePacket.getData(), 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Product receivedProduct = (Product) ois.readObject();

            System.out.println(">>> name: " + receivedProduct.getName());
            System.out.println(">>> quantity: " + receivedProduct.getQuantity());

            String part[] = receivedProduct.getName().trim().split(" ");
            String s = "";
            if (part.length > 1) {
                s = part[part.length-1] + " ";
                for (int i=1;i<part.length-1;i++) s += part[i] + " ";
            }
            s += part[0];
            receivedProduct.setName(s.trim());

            String x = receivedProduct.getQuantity() + "";
            receivedProduct.setQuantity(Integer.parseInt(new StringBuilder(x).reverse().toString()));

            System.out.println(">>> name: " + receivedProduct.getName());
            System.out.println(">>> quantity: " + receivedProduct.getQuantity());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(receivedProduct);

            byte[] sendServer = new byte[8 + baos.size()];
            System.arraycopy(res.getBytes(), 0, sendServer, 0, 8);
            System.arraycopy(baos.toByteArray(), 0, sendServer, 8, baos.size());
            sendPacket = new DatagramPacket(sendServer, sendServer.length, server, serverPort);
            socket.send(sendPacket);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
