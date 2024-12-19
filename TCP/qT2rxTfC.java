package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class qT2rxTfC {
    
    // Hàm tính ước chung lớn nhất (UCLN)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            // Kết nối tới server
            socket = new Socket("203.162.10.109", 2207);
          
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

          
            String studentCode = "B21DCCN562";
            String qCode = "qT2rxTfC";
            dataOutputStream.writeUTF(studentCode + ";" + qCode);
            dataOutputStream.flush();

            // Đọc hai số nguyên a và b từ server
            int a = dataInputStream.readInt();
            int b = dataInputStream.readInt();
            System.out.println("Received numbers: a = " + a + ", b = " + b);

            // Tính toán UCLN, BCNN, tổng và tích
            int UCLN = gcd(a, b);
            int BCNN = a * b / UCLN;
            int sum = a + b;
            int product = a * b;

            // Gửi kết quả tính toán cho server
            dataOutputStream.writeInt(UCLN);
            dataOutputStream.writeInt(BCNN);
            dataOutputStream.writeInt(sum);
            dataOutputStream.writeInt(product);
            dataOutputStream.flush();

        } catch (IOException e) {
            // Bắt lỗi kết nối và I/O
            System.err.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // Đảm bảo đóng các kết nối và stream
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
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
