package packets;

import java.nio.charset.Charset;

public interface Constants {
    public static final Charset CHARSET = Charset.forName("UTF-8");
    public static final byte MAGICNUMBER = 42;
}
