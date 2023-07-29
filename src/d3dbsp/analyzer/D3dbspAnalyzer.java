package d3dbsp.analyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class D3dbspAnalyzer {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("X:/Program Files (x86)/Steam/steamapps/common/Call of Duty World at War/raw/maps/ps3_test.d3dbsp", "r");
        if (!D3dbspUtil.isValidHeader(D3dbspUtil.getHeader(file))) {
            System.exit(0);
        }
        System.out.println("Valid d3dbsp file");
        System.out.println("Version: 0x"+ String.format("%02X", D3dbspUtil.getVersionNum()) + " | " + D3dbspUtil.getVersionNum());
        System.out.println("Lump Count: 0x"+ String.format("%02X", D3dbspUtil.getLumpCount()) + " | " + D3dbspUtil.getLumpCount());
    }
}
