package d3dbsp.analyzer;

import datatypes.Lump;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class D3dbspAnalyzer {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Input a file path");
        String path = s.nextLine();
        RandomAccessFile file = new RandomAccessFile(path, "r");
        if (!D3dbspUtil.isValidHeader(D3dbspUtil.getHeader(file))) {
            System.exit(0);
        }
        System.out.println("Valid d3dbsp file");
        System.out.println("Version: 0x"+ String.format("%02X", D3dbspUtil.getVersionNum()) + " | " + D3dbspUtil.getVersionNum());
        System.out.println("Lump Count: 0x"+ String.format("%02X", D3dbspUtil.getLumpCount()) + " | " + D3dbspUtil.getLumpCount());
        
        ArrayList<Lump> lumps = D3dbspUtil.parseLumpIndex();
        lumps.forEach(l -> {
            System.out.println("Lump id: " + D3dbspUtil.bytesToHex(l.getLumpID()) + " | " + D3dbspUtil.bytesToDecimal(l.getLumpID()) + " Lump length: " + D3dbspUtil.bytesToHex(l.getLumpLength()) + " | " + D3dbspUtil.bytesToDecimal(l.getLumpLength()) + "\t Start offset " + l.calculateStartOffset() + " End offset " + l.calculateEndOffset());
        });
    }
}
