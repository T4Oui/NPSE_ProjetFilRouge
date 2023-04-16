package vueconsole;

import java.util.Scanner;

public class Clavier {
    private static Scanner scanner = new Scanner(System.in);

    public static int entrerClavierInt() {
        return scanner.nextInt();
    }

    public static String entrerClavierString() {
    	Scanner scannerString = new Scanner(System.in);
        return scannerString.nextLine();
    }
    
    public static float entrerClavierFloat()
    {
    	return scanner.nextFloat();
    }
    
   
}