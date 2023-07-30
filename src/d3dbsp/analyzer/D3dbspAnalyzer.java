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
        for(int i = 0; i < lumps.size(); i++) {
            if (D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpID()) == 0) {
                lumps.get(i).calculateOffsets(0);
            }
            else {
                lumps.get(i).calculateOffsets(lumps.get(i-1).getLumpEndOffset());
            }
            System.out.println("Lump id: " + D3dbspUtil.bytesToHex(lumps.get(i).getLumpID()) + " | " + D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpID()) + " Lump length: " + D3dbspUtil.bytesToHex(lumps.get(i).getLumpLength()) + " | " + D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpLength()) + "\t Start offset " + lumps.get(i).getLumpStartOffset() + " End offset " + lumps.get(i).getLumpEndOffset()+ "\t Name: " + lumps.get(i).getLumpName());
        }
            
        
    }
}
