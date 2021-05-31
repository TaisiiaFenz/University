import java.math.BigInteger;

public class MathUtils {
    public static final BigInteger THREE = BigInteger.valueOf(3);

    public static void xor(int[] a, int[] b, int[] resultArray) {
        for (int i = 0; i < a.length; i++)
            resultArray[i] = a[i] ^ b[i];
    }

    public static int[] toIntArray(byte[] bytes) {
        if (bytes.length % 4 != 0)
            throw new IllegalArgumentException("Bytes count should be divisible by 4");
        int[] ints = new int[bytes.length / 4];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (bytes[i * 4] & 0xFF) << 24
                    | (bytes[i * 4 + 1] & 0xFF) << 16
                    | (bytes[i * 4 + 2] & 0xFF) << 8
                    | (bytes[i * 4 + 3] & 0xFF);
        }
        return ints;
    }

    public static byte[] toByteArray(int[] ints) {
        byte[] bytes = new byte[ints.length * Integer.BYTES];
        for (int i = 0; i < ints.length; i++) {
            bytes[i * 4] = (byte)(ints[i] >> 24);
            bytes[i * 4 + 1] = (byte)(ints[i] >> 16);
            bytes[i * 4 + 2] = (byte)(ints[i] >> 8);
            bytes[i * 4 + 3] = (byte)ints[i];
        }
        return bytes;
    }
}
