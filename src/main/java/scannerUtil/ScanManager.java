package scannerUtil;

import java.util.Scanner;

/**
 * Created by Anastasiya on 12.02.2017.
 */
public class ScanManager {
    private static Scanner readValue;

    public static String consoleReadString() {
        readValue = new Scanner(System.in);
        return readValue.nextLine();
    }

    public static void scannerClose() {
        readValue.close();
    }
}
