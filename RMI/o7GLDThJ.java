package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class o7GLDThJ {
    public static String intToRoman(int num) {
       
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
           
            while (num >= values[i]) {
                result.append(symbols[i]); 
                num -= values[i]; 
            }
        }

        return result.toString();
    }
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) registry.lookup("RMICharacterService");
        String data = sv.requestCharacter("B21DCCN562", "o7GLDThJ");
        sv.submitCharacter("B21DCCN562", "o7GLDThJ", intToRoman(Integer.parseInt(data)));
    }
    
}
