package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class zVj4gjlJClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        try {
            // Kết nối tới server
            socket = new Socket("203.162.10.109", 2208);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gửi chuỗi mã sinh viên và mã câu hỏi
            String studentCode = "B15DCCN999";
            String qCode = "5E263AE1";
            writer.write(studentCode + ";" + qCode);
            writer.newLine();  // Thêm dòng mới để phân biệt các yêu cầu
            writer.flush();  // Đảm bảo dữ liệu được gửi đi ngay lập tức

            // Đọc chuỗi từ server
            String serverResponse = reader.readLine();
            System.out.println("Received from server: " + serverResponse);

            // Tách chuỗi nhận được thành hai phần: 
            // 1. Các ký tự và số
            // 2. Các ký tự đặc biệt
            String alphanumericPart = serverResponse.replaceAll("[^a-zA-Z0-9]", "");
            String specialCharPart = serverResponse.replaceAll("[a-zA-Z0-9]", "");
            
            // In ra các phần đã tách
            System.out.println("Alphanumeric part: " + alphanumericPart);
            System.out.println("Special characters part: " + specialCharPart);

            // Gửi lại hai phần chuỗi lên server
            writer.write(alphanumericPart);
            writer.newLine();
            writer.write(specialCharPart);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // Đảm bảo đóng tài nguyên sau khi sử dụng
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
