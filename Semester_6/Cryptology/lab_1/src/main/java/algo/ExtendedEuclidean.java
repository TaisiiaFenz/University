package algo;

import java.math.BigInteger;

import static algo.BigIntegerUtils.random;

public class ExtendedEuclidean {
    public static BigInteger[] compute(BigInteger a, BigInteger b) {
        BigInteger[] result = new BigInteger[3];
        if (a.equals(BigInteger.ZERO)) {
            result[0] = b;
            result[1] = BigInteger.ZERO;
            result[2] = BigInteger.ONE;
        } else {
            BigInteger[] previousResult = compute(b.mod(a), a);
            result[0] = previousResult[0];
            result[1] = previousResult[2].subtract(b.divide(a).multiply(previousResult[1]));
            result[2] = previousResult[1];
        }
        return result;
    }

    public static BigInteger diophantine(
            BigInteger a,
            BigInteger b,
            BigInteger c,
            BigInteger[] euclidean
    ) {
        if (c.equals(BigInteger.ZERO)) {
            // There is no res for this parameters
            return null;
        }

        System.out.println(Arrays.toString(euclidean));

        BigInteger x = euclidean[1].multiply(c).divide(euclidean[0]);
//        BigInteger y = euclidean[2].multiply(c).divide(euclidean[0]);

        return x;
    }
}
