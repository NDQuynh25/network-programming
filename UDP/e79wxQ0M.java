package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javafx.scene.chart.PieChart.Data;

public class e79wxQ0M {
    public static void main(String[] args) {
        String serverHost = "203.162.10.109";
        int serverPort = 2208;
        String studentCode = "B21DCCN562";
        String qCode = "e79wxQ0M";
        try {
            InetAddress serverAddress = InetAddress.getByName(serverHost);
            DatagramSocket clientSocket = new DatagramSocket();
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendBuffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String res = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + res);

            String[] parts = res.split(";");
            String requestId = parts[0];
            String str1 = parts[1];
            String str2 = parts[2];
            for (int i = 0; i < str2.length(); i++) {
                
                while (true) {
                    int n = str1.indexOf(str2.charAt(i));
                    if (n == -1) {
                        break;
                    } 
                    else str1 = str1.substring(0, n) + str1.substring(n + 1);
                }
            }

            StringBuilder missingMessage = new StringBuilder(requestId + ";" + str1);
            sendBuffer = missingMessage.toString().getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
