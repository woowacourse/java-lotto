package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EarningRateTest {

    @ParameterizedTest
    @MethodSource("calculate_earningRate_testcase")
    void calculate_earningRate(BigInteger totalPrize, BigInteger paymentAmount, BigInteger expected) {
        EarningRate earningRate = new EarningRate(totalPrize, paymentAmount);

        assertThat(earningRate.toBigInteger()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculate_earningRate_testcase() {
        // totalPrize, paymentAmount, expected
        return Stream.of(
                Arguments.of(toBigInteger(1000), toBigInteger(1000), toBigInteger(100)),
                Arguments.of(toBigInteger(0), toBigInteger(1000), toBigInteger(0)),
                Arguments.of(toBigInteger(500), toBigInteger(1000), toBigInteger(50))
        );
    }

    private static BigInteger toBigInteger(long i) {
        return BigInteger.valueOf(i);
    }
}