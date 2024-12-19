package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class nHiOh969 {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String data = sv.requestData("B21DCCN562", "nHiOh969").toString();
        String data1[] = data.split(", ");
        float x = 0;
        for (int i=0;i<data1.length;i++) {
            x += Float.parseFloat(data1[i]);
        }
        float tb = x/(data1.length);
       
        float tg = 0;
        for (int i=0;i<data1.length;i++) {
            tg += (Float.parseFloat(data1[i])-tb)*(Float.parseFloat(data1[i])-tb);
        }
        float px = tg/(data1.length);
        double dlc = Math.sqrt(px);
        System.out.println(px + " " + dlc);
        String kq = String.format("%.2f : %.2f", px, dlc);
        sv.submitData("B21DCCN562", "nHiOh969", kq);



    }
}
