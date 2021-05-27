package algo;

import java.math.BigInteger;

import static algo.BigIntegerUtils.random;

public class MillerRabin {
    private final int k;
    private final long seed;

    public MillerRabin(int k) {
        this.k = k;
        this.seed = System.currentTimeMillis();
    }

    public MillerRabin(int k, long seed) {
        this.k = k;
        this.seed = seed;
    }

    public boolean test(BigInteger n) {
        if (n.compareTo(BigInteger.valueOf(4)) < 0)
            throw new IllegalArgumentException("Number should be greater then 0");
        if (n.compareTo(BigInteger.valueOf(4)) == 0)
            return false;
        for (int i = 0; i < k; i++)
            if (!singleRandom(n)) return false;
        return true;
    }

    private boolean singleRandom(BigInteger n) {
        BigInteger temp = random(BigInteger.ONE, n, seed);

        if (!n.gcd(temp).equals(BigInteger.ONE)) return false;

        BigInteger base = n.subtract(BigInteger.ONE);
        BigInteger TWO = new BigInteger("2");

        int k = 0;
        while ((base.mod(TWO)).equals(BigInteger.ZERO)) {
            base = base.divide(TWO);
            k++;
        }

        BigInteger curValue = temp.modPow(base, n);

        if (curValue.equals(BigInteger.ONE))
            return true;

        for (int i = 0; i < k; i++) {
            if (curValue.equals(n.subtract(BigInteger.ONE)))
                return true;
            else
                curValue = curValue.modPow(TWO, n);
        }

        return false;
    }
}
