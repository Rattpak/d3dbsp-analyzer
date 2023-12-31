package d3dbsp.analyzer;

import datatypes.Lump;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class D3dbspUtil {
    private static RandomAccessFile file;
    private static byte totalLumpCount = -1;
    private static ArrayList<Lump> lumps;
    
    /**
     * Reads the header of a binary file using a RandomAccessFile object.
     *
     * @param f The RandomAccessFile object representing the binary file.
     * @return A byte array containing the header read from the file. The header has a length of 4 bytes.
     * @throws IOException If an I/O error occurs while reading the header.
     */
    public static byte[] getHeader(RandomAccessFile f) {
        try {
            f.seek(0x0);
            byte[] buffer = new byte[4]; // get header
            file = f;
            return buffer;
        } catch (IOException ex) {
            Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Checks if the provided byte array matches the valid header sequence.
     *
     * @param b The byte array to be checked for a valid header.
     * @return true if the byte array contains a valid header, false otherwise.
     * @throws IOException If an I/O error occurs while reading the bytes from the file.
     */
    public static boolean isValidHeader(byte[] b) throws IOException {
        return file.read(b) == b.length && java.util.Arrays.equals(b, new byte[] { 0x49, 0x42, 0x53, 0x50 });
    } 
    
    public static void getAndParseAllLumps() {
        lumps = D3dbspUtil.parseLumpIndex();
        for(int i = 0; i < lumps.size(); i++) {
            if (D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpID()) == 0) {
                lumps.get(i).calculateOffsets(0);
            }
            else {
                lumps.get(i).calculateOffsets(lumps.get(i-1).getLumpEndOffset());
            }
            //System.out.println("Lump id: 0x" + D3dbspUtil.bytesToHex(lumps.get(i).getLumpID()) + " | " + D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpID()) + " Lump length: 0x" + D3dbspUtil.bytesToHex(lumps.get(i).getLumpLength()) + " | " + D3dbspUtil.bytesToDecimal(lumps.get(i).getLumpLength()) + "\t Start offset " + lumps.get(i).getLumpStartOffset() + " End offset " + lumps.get(i).getLumpEndOffset()+ "\t Name: " + lumps.get(i).getLumpName());
        }
    }
    
    public static ArrayList<Lump> getLumpsArray() {
        return lumps;
    }
    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    
    public static long bytesToDecimal(byte[] bytes) {
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result = (result << 8) + (bytes[i] & 0xFF);
        }
        return result;
    }
    
    public static byte getVersionNum() {
        try {
            file.seek(0x4);
            byte[] buffer = new byte[1];
            int bytesRead = file.read(buffer);
            if (bytesRead == 1) {
                return buffer[0];
            } else {
                Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, "Failed to read the byte at offset 0x4");
            }
        } catch (IOException ex) {
            Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static byte getLumpCount() {
        if(totalLumpCount != -1) {
            return totalLumpCount;
        }
        try {
            file.seek(0x8);
            byte[] buffer = new byte[1];
            int bytesRead = file.read(buffer);
            if (bytesRead == 1) {
                return buffer[0];
            } else {
                Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, "Failed to read the byte at offset 0x8");
            }
        } catch (IOException ex) {
            Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static ArrayList<Lump> parseLumpIndex() {
       ArrayList<Lump> lumps = new ArrayList<>();
        try {
            int lumpCount = D3dbspUtil.getLumpCount();
            file.seek(0xC); // lump start at 0xC

            for (int i = 0; i < lumpCount; i++) {
                byte[] buffer = new byte[8];
                int bytesRead = file.read(buffer);

                if (bytesRead == buffer.length) {
                    // Split the buffer into two separate arrays for id and len
                    byte[] id = new byte[4];
                    byte[] len = new byte[4];
                    System.arraycopy(buffer, 0, id, 0, 4);
                    System.arraycopy(buffer, 4, len, 0, 4);

                    // Create a new Lump object and add it to the ArrayList
                    lumps.add(new Lump(id, len));

                    // Move the file pointer to the next lump entry (8 bytes ahead of previous)
                    file.seek(file.getFilePointer());
                } else {
                    Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, "Failed to read lump data");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(D3dbspUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lumps;
    }
}
