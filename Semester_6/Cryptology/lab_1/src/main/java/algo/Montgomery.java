package algo;

import java.math.BigInteger;

import static algo.BigIntegerUtils.random;

public class Montgomery {
    private final BigInteger mod;
    private final BigInteger negativeMod;
    private final BigInteger r;
    private final int shift;

    public Montgomery(BigInteger mod) {
        if (mod.compareTo(BigInteger.ZERO) <= 0)
            throw new IllegalArgumentException("Mod should be positive");

        this.mod = mod;
        this.shift = mod.bitLength();

        this.r = BigInteger.ONE.shiftLeft(shift);
        this.negativeMod = ExtendedEuclidean.compute(mod, r)[1].negate();
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        BigInteger aMont = getMont(a);
        BigInteger bMont = getMont(b);

        return multiplyMont(aMont, bMont);
    }

    public BigInteger pow(BigInteger number, BigInteger power) {
        BigInteger numberMont = getMont(number);
        BigInteger resMont = getMont(BigInteger.ONE);

        while (!power.equals(BigInteger.ZERO)) {
            if (power.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                resMont = multiplyMont(resMont, numberMont);
            }
            numberMont = multiplyMont(numberMont, numberMont);
            power = power.shiftRight(1);
        }

        return multiplyMont(resMont, BigInteger.ONE);
    }

    private BigInteger getMont(BigInteger n) {
        return n.shiftLeft(shift).mod(mod);
    }

    private BigInteger multiplyMont(BigInteger aMont, BigInteger bMont) {
        BigInteger t = aMont.multiply(bMont);
        BigInteger u = t.add(
                t.multiply(negativeMod)
                        .and(r.subtract(BigInteger.ONE))
                        .multiply(mod)).shiftRight(shift);

        if (u.compareTo(mod) > 0)
            u = u.subtract(mod);
        return u;
    }
}
