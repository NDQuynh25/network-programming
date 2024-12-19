package TCP;

import java.io.*;
import java.net.Socket;

public class Owl3BUb { //5Owl3BUb
    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            socket = new Socket("203.162.10.109", 2209);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

          
            String studentCode = "B21DCCN562";
            String qCode = "5Owl3BUb";
            objectOutputStream.writeObject(studentCode + ";" + qCode);
            objectOutputStream.flush();

           
            Laptop laptop = (Laptop) objectInputStream.readObject();
            String name[] = laptop.getName().split(" ");
            String s = name[name.length-1] + " ";
            for (int i=1;i<name.length-1;i++) s += name[i] + " ";
            s += name[0];
            laptop.setName(s);
            System.out.println(s);

            String x = new StringBuffer(laptop.getQuantity() + "").reverse().toString();
            laptop.setQuantity(Integer.parseInt(x));


        
            objectOutputStream.writeObject(laptop);
            objectOutputStream.flush();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
                if (objectInputStream != null) objectInputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
