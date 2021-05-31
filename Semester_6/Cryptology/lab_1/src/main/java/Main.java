/*
[x]	Реализовать тест Ферма
[x]	Реализовать тест Миллера - Рабина
[x]	Реализовать операцию возведения по модулю в степень методом двоичного потенцирования
[x]	Реализовать умножение Карацубы.
[x]	Арифметика Монтгомери
[x]	Расширенный алгоритм Евклида
Считать, что все числа в алгоритмах имеют длину не менее 512 бит.
*/

import algo.*;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        System.out.println(BigInteger.valueOf(20));
//
//        // Fermat prime number test
//        System.out.println(
//                new Fermat(10).test(BigInteger.valueOf(5))
//        );
//
//        // Miller-Rabin prime number test
//        System.out.println(
//                new MillerRabin(10).test(BigInteger.valueOf(7))
//        );
//
//        // Binary power
//        System.out.println(
//                BinaryPower.pow(BigInteger.TWO, BigInteger.valueOf(5), BigInteger.valueOf(10000))
//        );
//
//        // Multiply two numbers using Karatsuba algorithm
//        System.out.println(
//                Karatsuba.multiply(BigInteger.TWO, BigInteger.valueOf(5))
//        );
//
//        // Montgomery algorithm to multiply and power by mod
//        System.out.println(
//                new Montgomery(BigInteger.valueOf(10000)).multiply(BigInteger.TWO, BigInteger.valueOf(5))
//        );
//        System.out.println(
//                new Montgomery(BigInteger.valueOf(10000)).pow(BigInteger.TWO, BigInteger.valueOf(5))
//        );

        // Extended Euclidean to find GCD and numbers to represent sum
        System.out.println(Arrays.toString(
                ExtendedEuclidean.compute(BigInteger.valueOf(17), BigInteger.valueOf(35))
        ));

        // Diophantine Equation
        System.out.println(ExtendedEuclidean.diophantine(
                BigInteger.valueOf(19),
                BigInteger.valueOf(51),
                BigInteger.valueOf(91),
                ExtendedEuclidean.compute(BigInteger.valueOf(19), BigInteger.valueOf(51))
        ));

        var a = BigInteger.valueOf(19);
        var b = BigInteger.valueOf(91);
        var c = BigInteger.valueOf(51);

        var res = ExtendedEuclidean.compute(a, b);
        System.out.println("res " + Arrays.toString(res));

        var x = ExtendedEuclidean.diophantine(a, b, c, res);

        System.out.println(a + " * " + x + " = " + a.multiply(x).mod(b) + "(mod " + b + ")");

        var test =  ExtendedEuclidean.compute(BigInteger.valueOf(15), BigInteger.valueOf(37));
        var testx =
                ExtendedEuclidean.diophantine(BigInteger.valueOf(15),
                        BigInteger.valueOf(37), BigInteger.valueOf(1), test);
        System.out.println(testx);
    }
}
