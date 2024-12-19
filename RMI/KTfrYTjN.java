package RMI;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class KTfrYTjN {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Serializable book = sv.requestObject("B21DCCN562", "KTfrYTjN");
        Book x = (Book)book;
        String partName[] = x.getAuthor().split(" ");
        System.out.println(x.getAuthor());
        String name = "";

        
        name = (partName[0].charAt(0) + "").toUpperCase() + ("" + partName[partName.length-1].charAt(0)).toUpperCase();
        
        System.out.println(name);
        int year = Integer.parseInt(String.format("%02d", x.getYearPublished() % 100));
        System.out.println(year);
        String title = x.getTitle().replaceAll("[^a-zA-Z]", "");
        int sl = title.length();
        System.out.println(sl);
        String pageCount = x.getPageCount() + "";
        while (true) {
            if (pageCount.length() >=3 ) break;
            else pageCount = "0" + pageCount;
        }
        System.out.println(pageCount);
        String code = name + year + sl + pageCount;
        x.setCode(code);
        sv.submitObject("B21DCCN562", "KTfrYTjN", x);





    }
}
