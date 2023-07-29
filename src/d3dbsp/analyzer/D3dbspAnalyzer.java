package d3dbsp.analyzer;

import datatypes.Lump;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class D3dbspAnalyzer {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("X:/Program Files (x86)/Steam/steamapps/common/Call of Duty World at War/raw/maps/ps3_test.d3dbsp", "r");
        if (!D3dbspUtil.isValidHeader(D3dbspUtil.getHeader(file))) {
            System.exit(0);
        }
        System.out.println("Valid d3dbsp file");
        System.out.println("Version: 0x"+ String.format("%02X", D3dbspUtil.getVersionNum()) + " | " + D3dbspUtil.getVersionNum());
        System.out.println("Lump Count: 0x"+ String.format("%02X", D3dbspUtil.getLumpCount()) + " | " + D3dbspUtil.getLumpCount());
        
        ArrayList<Lump> lumps = D3dbspUtil.parseLumpIndex();
        //System.out.println(lumps.size());
        for (Lump l : lumps) {
            // Convert byte arrays to meaningful representations
            String lumpIDHex = D3dbspUtil.bytesToHex(l.getLumpID());
            String lumpLengthHex = D3dbspUtil.bytesToHex(l.getLumpLength());

            System.out.println("Lump id: " + lumpIDHex + " Lump length: " + lumpLengthHex);
        }
    }
}
