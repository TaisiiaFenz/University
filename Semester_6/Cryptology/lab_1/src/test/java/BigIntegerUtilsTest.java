import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static algo.BigIntegerUtils.pow;
import static algo.BigIntegerUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BigIntegerUtilsTest {
    @Test
    void randomTest() {
        assertTrue(random(BigInteger.ONE, BigInteger.TEN, 0).compareTo(BigInteger.TEN) < 0);
        assertTrue(random(BigInteger.ONE, BigInteger.TEN, 0).compareTo(BigInteger.ZERO) > 0);
        assertEquals(BigInteger.ONE, random(BigInteger.ONE, BigInteger.TWO, 0));
    }

    @Test
    void powTest() {
        assertEquals(BigInteger.valueOf(32), pow(BigInteger.TWO, BigInteger.valueOf(5)));
        assertEquals(BigInteger.TWO, pow(BigInteger.TWO, BigInteger.valueOf(1)));
    }
}
