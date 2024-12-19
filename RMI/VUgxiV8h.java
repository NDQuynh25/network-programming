package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import RMI.ByteService;

public class VUgxiV8h  {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] data = sv.requestData("B21DCCN562", "VUgxiV8h");
        byte[] key = "PTIT".getBytes();
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ key[i % key.length]);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.println(result[i] + " ");
        }
        sv.submitData("B21DCCN562", "VUgxiV8h", result);
    }
}
     
