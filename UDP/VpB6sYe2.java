package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class VpB6sYe2 {
    public static void main(String[] args) {
        String serverHost = "203.162.10.109";
        int serverPort = 2207;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(serverHost);

            String studentCode = "B21DCCN562";
            String qCode = "VpB6sYe2";
            String message = ";" + studentCode + ";" + qCode;

            byte[] sendBuffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

           
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + serverResponse);

        
            String[] parts = serverResponse.split(";");
            String requestId = parts[0];
            int n = Integer.parseInt(parts[1]);
            String[] receivedNumbers = parts[2].split(",");

           
            List<Integer> missingNumbers = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                boolean found = false;
                for (String num : receivedNumbers) {
                    if (Integer.parseInt(num) == i) {
                        found = true;
                        break;
                    }
                }
                if (!found) missingNumbers.add(i);
            }

            // Tạo thông điệp gửi lại server
            StringBuilder missingMessage = new StringBuilder(requestId + ";");
            for (int num : missingNumbers) {
                missingMessage.append(num).append(",");
            }
            if (missingNumbers.size() > 0) {
                missingMessage.deleteCharAt(missingMessage.length() - 1); 
            }

            sendBuffer = missingMessage.toString().getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            System.out.println("Sent missing numbers: " + missingMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
