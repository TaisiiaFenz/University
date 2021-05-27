package algo;

import java.math.BigInteger;

import static algo.BigIntegerUtils.random;

public class BinaryPower {
    public static BigInteger pow(BigInteger number, BigInteger power, BigInteger mod) {
        BigInteger result = BigInteger.ONE;

        while (!power.equals(BigInteger.ZERO)) {
            if (power.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                result = result.multiply(number);
                if (mod != null)
                    result = result.mod(mod);
            }
            number = number.multiply(number);
            if (mod != null)
                number = number.mod(mod);
            power = power.shiftRight(1);
        }
        return result;
    }
}
