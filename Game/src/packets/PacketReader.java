package packets;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketReader {
    //Packet definition:
    //1 Byte  -> Magic Number
    //2 Short -> PacketID
    //3 Short -> Packet length
    //4 XXXXX -> 1 Byte per per length
    private byte[] bytes;
    private short packetId;
    public static final byte MAGICNUMBER = 42;
    
    public byte[] GetData(){
        return bytes;
    }
    
    public boolean ReadData(DataInputStream stream){
        try {
            //Check Magic Number
            if (stream.readByte() != MAGICNUMBER) {
                System.out.println("Invalid Packet!");
                return false;
            }
            //Get Packet ID
            packetId = stream.readShort();
            //Read Packet length
            int length = stream.readShort();
            bytes = new byte[length];
            //Read Packet Body
            length = stream.read(bytes);
            if (length <= 0) {
                return false;
            }
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
