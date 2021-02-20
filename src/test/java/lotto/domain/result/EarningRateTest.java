package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EarningRateTest {

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("calculate_earningRate_testcase")
    void calculate_earningRate(BigInteger totalPrize, BigInteger paymentAmount,
            BigInteger expected) {
        EarningRate earningRate = new EarningRate(totalPrize, paymentAmount);

        assertThat(earningRate.toBigInteger()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculate_earningRate_testcase() {
        return Stream.of(
                Arguments.of(toBigInteger(1000), toBigInteger(1000), toBigInteger(100)),
                Arguments.of(toBigInteger(0), toBigInteger(1000), toBigInteger(0)),
                Arguments.of(toBigInteger(500), toBigInteger(1000), toBigInteger(50)),
                Arguments.of(toBigInteger(0), toBigInteger(0), toBigInteger(100))
        );
    }

    private static BigInteger toBigInteger(long i) {
        return BigInteger.valueOf(i);
    }
}