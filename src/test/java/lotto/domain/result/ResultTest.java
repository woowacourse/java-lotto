package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("getEarningRate_testcase")
    void getEarningRate(Map<Rank, Integer> resultMap, BigInteger paymentAmount,
            BigInteger expectedEarningRate) {
        Result result = new Result(resultMap, paymentAmount);

        assertThat(result.getEarningRate()).isEqualTo(expectedEarningRate);
    }

    private static Stream<Arguments> getEarningRate_testcase() {
        return Stream.of(
                Arguments.of(
                        new HashMap<Rank, Integer>() {{
                            put(Rank.FIRST_PLACE, 1);
                        }},
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(200_000_000)
                ),
                Arguments.of(
                        new HashMap<Rank, Integer>() {{
                            put(Rank.UNRANKED, 1);
                        }},
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(0)
                ),
                Arguments.of(
                        new HashMap<Rank, Integer>() {{
                            put(Rank.FOURTH_PLACE, 1);
                            put(Rank.FIFTH_PLACE, 1);
                        }},
                        BigInteger.valueOf(2000),
                        BigInteger.valueOf(2750)
                ),
                Arguments.of(
                        new HashMap<Rank, Integer>() {{
                            put(Rank.FOURTH_PLACE, 2);
                            put(Rank.FIFTH_PLACE, 1);
                            put(Rank.UNRANKED, 1);
                        }},
                        BigInteger.valueOf(4000),
                        BigInteger.valueOf(2625)
                )
        );
    }
}