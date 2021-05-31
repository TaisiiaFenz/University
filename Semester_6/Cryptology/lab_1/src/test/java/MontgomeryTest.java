import algo.BigIntegerUtils;
import algo.Montgomery;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MontgomeryTest {
    private static final int ROUNDS = 16;

    @Test
    void wrongMod() {
        assertThrows(IllegalArgumentException.class, () -> new Montgomery(BigInteger.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new Montgomery(BigInteger.valueOf(-128)));
        assertDoesNotThrow(() -> new Montgomery(BigInteger.ONE));
    }

    @Test
    void multiply() {
        for (int i = 0; i < ROUNDS; i++) {
            BigInteger a = BigIntegerUtils.random();
            BigInteger b = BigIntegerUtils.random();
            BigInteger m = BigIntegerUtils.random();

            assertEquals(
                    a.multiply(b).mod(m),
                    new Montgomery(m).multiply(a, b)
            );
        }
    }

    @Test
    void pow() {
        for (int i = 0; i < ROUNDS; i++) {
            BigInteger a = BigIntegerUtils.random();
            BigInteger b = BigIntegerUtils.random();
            BigInteger m = BigIntegerUtils.random();

            assertEquals(
                    a.modPow(b, m),
                    new Montgomery(m).pow(a, b)
            );
        }
    }
}
