import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CastTest {
    byte[] key = { 0x01, 0x45, 0x67, 0x12, 0x34, 0x56, 0x78, 0x23, 0x45, 0x67, (byte) 0x89, 0x34, 0x56, 0x78,
            (byte) 0x9A};

    byte[] message = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

    @Test
    void encrypt() throws Exception {
        String str = "";
        for (int j = 0; j < message.length; j++) {
            str = str + Integer.toHexString(message[j] & 0xFF) + " ";
        }
        Cast cast = new Cast(key, message);
        cast.makeKey();
        cast.encrypt();
        cast.printCryptotext();
        assertEquals("a7 b8 4f a0 96 93 fc 62 ", cast.cryptoText);
    }

    @Test
    void decrypt() throws Exception {
        String str = "";
        for (int j = 0; j < message.length; j++) {
            str = str + Integer.toHexString(message[j] & 0xFF) + " ";
        }
        Cast cast = new Cast(key, message);
        cast.makeKey();
        cast.encrypt();
        cast.decrypt();
        cast.printPlaintext();
        assertEquals("1 23 45 67 89 ab cd ef ", cast.plainedText);
    }
}
