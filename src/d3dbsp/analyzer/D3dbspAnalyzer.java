package d3dbsp.analyzer;

import java.io.IOException;
import java.io.RandomAccessFile;

public class D3dbspAnalyzer {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("X:/Program Files (x86)/Steam/steamapps/common/Call of Duty World at War/raw/maps/ps3_test.d3dbsp", "r");
            file.seek(0x0);
            byte[] buffer = new byte[4]; // check header
            int bytesRead = file.read(buffer);
            if (bytesRead >= 0) {
                byte[] validHeader = { 0x49, 0x42, 0x53, 0x50 };
                boolean isValidHeader = java.util.Arrays.equals(buffer, validHeader);
                //for (int i = 0; i < bytesRead; i++) {
                //    System.out.printf("Byte at address 0x%02X: 0x%02X\n", i, buffer[i]);
                //}
                if (!isValidHeader) {
                    System.out.println("Invalid or unsupported d3dbsp file");
                    System.exit(0);
                }
                else {
                    System.out.println("Valid header");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}
