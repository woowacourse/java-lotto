package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

public class LottoCalculatorTest {

    @DisplayName("당첨된 복권의 총 합계 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1,5000", "MATCH_FOUR,2,100000", "MATCH_FIVE,3,4500000"})
    void 총합계를_계산하는_메서드(String winningStr, int winningCount, int expected) {
        HashMap<String, Integer> map = new HashMap<>();
        LottoCalculator lottoCalculator = new LottoCalculator();

        map.put(winningStr, winningCount);

        Assertions.assertThat(lottoCalculator.getTotalWinningPrice(map)).isEqualTo(expected);
    }

    @DisplayName("수익률을 리턴해주는 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1,1000,500", "MATCH_FOUR,2,1000,10000", "MATCH_FIVE,3,4500,100000"})
    void 수익률_계산_메서드(String winningStr, int winningCount, int money, int expected) {
        HashMap<String, Integer> map = new HashMap<>();
        LottoCalculator lottoCalculator = new LottoCalculator();

        map.put(winningStr, winningCount);

        Assertions.assertThat(lottoCalculator.getProfit(map, money)).isEqualTo(expected);
    }


}
