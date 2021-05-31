import algo.BigIntegerUtils;
import algo.ExtendedEuclidean;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ExtendedEuclideanTest {
    @Test
    void zero() {
        BigInteger random = BigIntegerUtils.random();
        assertArrayEquals(
                new BigInteger[]{random, BigInteger.ZERO, BigInteger.ONE},
                ExtendedEuclidean.compute(BigInteger.ZERO, random)
        );
    }

    @Test
    void mutual() {
        assertArrayEquals(
                BigIntegerUtils.intArrayToBigIntArray(1, -2, 1),
                ExtendedEuclidean.compute(BigInteger.valueOf(17), BigInteger.valueOf(35))
        );
    }

    @Test
    void compute() {
        assertArrayEquals(
                BigIntegerUtils.intArrayToBigIntArray(7, 1, -1),
                ExtendedEuclidean.compute(BigInteger.valueOf(21), BigInteger.valueOf(14))
        );
    }
}
