import algo.BigIntegerUtils;
import algo.BinaryPower;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryPowerTest {
    private static final int ROUNDS = 16;

    @Test
    void zero() {
        assertEquals(
                BigInteger.ONE,
                BinaryPower.pow(BigInteger.TWO, BigInteger.ZERO, BigInteger.TWO)
        );
    }

    @Test
    void one() {
        assertEquals(
                BigInteger.valueOf(4),
                BinaryPower.pow(BigInteger.valueOf(4), BigInteger.ONE, BigInteger.valueOf(5))
        );
    }

    @Test
    void random() {
        for (int i = 0; i < ROUNDS; i++) {

            BigInteger number = BigIntegerUtils.random();
            BigInteger power = BigIntegerUtils.random();
            BigInteger mod = BigIntegerUtils.random();

            assertEquals(
                    number.modPow(power, mod),
                    BinaryPower.pow(number, power, mod)
            );
        }
    }
}
