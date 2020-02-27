package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MoneyTest {
    private static Stream<String> MoneySetUp() {
        return Stream.of("", " ", null, "askjfakl", "0", "123");
    }

    @DisplayName("Should_유효성 통과_When_돈 입력에서 널값 및 공백, 숫자 이외 입력")
    @ParameterizedTest
    @MethodSource("MoneySetUp")
    void validateMoneyTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Should_수익률을 리턴_When_lottoResults와 money를 넣었을 때")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1,1000,500", "MATCH_FOUR,2,1000,10000", "MATCH_FIVE,3,4500,100000"})
    void calculateProfit(RankType winningStr, int winningCount, int money, int expected) {
        Map<RankType, Integer> lottoResults = new HashMap<>();

        lottoResults.put(winningStr, winningCount);

        Assertions.assertThat(Money.getProfit(lottoResults, money)).isEqualTo(expected);
    }
}
