package datatypes;

import d3dbsp.analyzer.D3dbspUtil;

public class Lump {
    private byte[] lumpID;
    private byte[] lumpLength;
    private String lumpName;
    
    public Lump(byte[] id, byte[] len) {
        this.lumpID = toLittleEndian(id);
        this.lumpLength = toLittleEndian(len);
        this.lumpName = getLumpNameFromId();
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
    
    public String getLumpName() {
        return this.lumpName;
    }
    
    private String getLumpNameFromId() {
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 0) {
            return "Materials";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 1) {
            return "Lightmaps";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 2) {
            return "Light Grid Points";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 3) {
            return "Light Grid Colors";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 4) {
            return "Planes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 5) {
            return "Brush Sides";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 6) {
            return "Brush Side Edge Counts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 7) {
            return "Brush Edges";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 8) {
            return "Brushes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 9) {
            return "Layered Tri Soups";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 10) {
            return "Layered Verts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 11) {
            return "Layered Indexes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 19) {
            return "Portal Verts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 24) {
            return "Layered AABB Trees";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 25) {
            return "Cells";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 26) {
            return "Portals";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 27) {
            return "Nodes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 28) {
            return "Leafs";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 29) {
            return "Leaf Brushes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 31) {
            return "Collision Verts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 32) {
            return "Collision Tris";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 33) {
            return "Collision Edge Walk";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 34) {
            return "Collision Borders";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 35) {
            return "Collision Parts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 36) {
            return "Collision AABBs";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 37) {
            return "Models";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 39) {
            return "Entities";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 40) {
            return "Paths";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 41) {
            return "Reflection Probes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 42) {
            return "Layered Data";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 43) {
            return "Primary Lights";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 44) {
            return "Light Grid Header";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 45) {
            return "Light Grid Rows";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 47) {
            return "Simple Tri Soups";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 48) {
            return "Simple Verts";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 49) {
            return "Simple Indexes";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 51) {
            return "Simple AABB Trees";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 52) {
            return "Light Regions";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 53) {
            return "Light Region Hulls";
        }
        if (D3dbspUtil.bytesToDecimal(this.lumpID) == 54) {
            return "Light Region Axes";
        }
        return "N/A";
    }
}
