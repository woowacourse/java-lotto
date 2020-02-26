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
    @DisplayName("복권 갯수 계산 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1500,1", "11000,11", "12345,12"}, delimiter = ',')
    void calculateLottoTicketTest(String input, int expected) {
        Money money = new Money(input);
        Assertions.assertThat(money.countLottoTicket()).isEqualTo(expected);
    }

    private static Stream<String> MoneySetUp() {
        return Stream.of("", " ", null, "askjfakl", "0", "123");
    }

    @DisplayName("돈 입력에서 널값 및 공백, 숫자 이외 테스트")
    @ParameterizedTest
    @MethodSource("MoneySetUp")
    void validateMoneyTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수익률을 리턴해주는 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1,1000,500", "MATCH_FOUR,2,1000,10000", "MATCH_FIVE,3,4500,100000"})
    void calculateProfit(RankType winningStr, int winningCount, int money, int expected) {
        Map<RankType, Integer> winningCountMap = new HashMap<>();

        winningCountMap.put(winningStr, winningCount);

        Assertions.assertThat(Money.getProfit(winningCountMap, money)).isEqualTo(expected);
    }
}
