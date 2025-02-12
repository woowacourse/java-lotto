package domain;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsCalculatorTest {
    
    @Test
    void 로또_당첨_통계를_계산한다() {
        //given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 4, 5, 6)),
                new Lotto(List.of(1, 9, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 10, 15, 5, 6)),
                new Lotto(List.of(1, 2, 11, 4, 5, 6))
        );
        List<Integer> matchNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        
        LottoStatisticsCalculator statisticsCalculator = new LottoStatisticsCalculator(lottos);
        
        //when
        EnumMap<LottoPrize, Integer> result = statisticsCalculator.statisticsCalculate(matchNumbers, bonusNumber);
        
        //then
        assertThat(result).containsEntry(LottoPrize.FIRST, 1);
        assertThat(result).containsEntry(LottoPrize.SECOND, 0);
        assertThat(result).containsEntry(LottoPrize.THIRD, 2);
        assertThat(result).containsEntry(LottoPrize.FOURTH, 1);
        assertThat(result).containsEntry(LottoPrize.FIFTH, 1);
    }
    
}