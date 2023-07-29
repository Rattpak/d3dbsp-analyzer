package d3dbsp.analyzer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class D3dbspUtil {
    private static RandomAccessFile file;
    
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
}
