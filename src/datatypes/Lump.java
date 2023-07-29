package datatypes;

import d3dbsp.analyzer.D3dbspUtil;

public class Lump {
    private byte[] lumpID;
    private byte[] lumpLength;
    
    public Lump(byte[] id, byte[] len) {
        this.lumpID = toLittleEndian(id);
        this.lumpLength = toLittleEndian(len);
    }
   
    /**
     * Get the lumpID array.
     *
     * @return The lumpID array.
     */
    public byte[] getLumpID() {
        return lumpID;
    }

    /**
     * Get the lumpLength array.
     *
     * @return The lumpLength array.
     */
    public byte[] getLumpLength() {
        return lumpLength;
    }
    
    public static byte[] toLittleEndian(byte[] bytes) {
        byte[] littleEndianBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            littleEndianBytes[i] = bytes[bytes.length - 1 - i];
        }
        return littleEndianBytes;
    }

    public long calculateStartOffset() {  
        int headerSize = 3;
        return ((headerSize + (D3dbspUtil.getLumpCount()*2))*4);
    }
    
    public long calculateEndOffset() {
        return calculateStartOffset() + D3dbspUtil.bytesToDecimal(this.lumpLength);
    }
}
