package algo;

import java.math.BigInteger;

import static algo.BigIntegerUtils.random;
public class Karatsuba {
    public static BigInteger multiply(BigInteger u, BigInteger v) {
        int length = Math.max(u.bitLength(), v.bitLength());
        if (length < 2)
            return u.multiply(v);

        int shift = length / 2;

        BigInteger x = BigInteger.ONE.shiftLeft(shift);

        BigInteger a = u.shiftRight(shift);
        BigInteger b = u.and(x.subtract(BigInteger.ONE));

        BigInteger c = v.shiftRight(shift);
        BigInteger d = v.and(x.subtract(BigInteger.ONE));

        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);

        BigInteger multValue = multiply(a.add(b), c.add(d)).subtract(ac).subtract(bd);
        return ac.shiftLeft(shift * 2).add(multValue.shiftLeft(shift)).add(bd);
    }
}
