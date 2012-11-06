/*
 * This Class represents a default Packet wich transports a UTF-8 String
 */
package packets;

import java.util.ArrayList;

public class Packet {
    public static final byte ID = 1;
    public static final String NAME = "Packet";
   
    private byte[] bytes;
    
    /**
     * Create a Packet from existing raw data
     * @param bytes 
     */
    public Packet(byte[] bytes){
        this.bytes = bytes;
    }
    
    /**
     * Create a Packet from a String
     * @param text 
     */
    public Packet(String text){
        this.bytes = text.getBytes(Constants.CHARSET);
    }
    
    /**
     * Get raw data from this packet
     * @return 
     */
    public byte[] getBodyData(){
        return bytes;
    }
    
    public byte[] getData(){
        byte[] b = new byte[bytes.length + 5];
        //Magicnumber
        b[0] = Constants.MAGICNUMBER;
        //Packetid
        b[1] = (byte)((ID>>8)&0xFF);
        b[2] = (byte)(ID&0xFF);
        //Length
        b[3] = (byte)((bytes.length>>8)&0xFF);
        b[4] = (byte)(bytes.length&0xFF);
        //Body
        for (int i = 0; i < bytes.length; i++) {
            b[i + 5] = bytes[i];
        }
        return b;
    }
    
    @Override
    public String toString(){
        return new String(bytes, Constants.CHARSET);
    }
}
