package domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StatisticsServiceTest {
    private StatisticsService statisticsService = new StatisticsService();

    @Test
    void 당첨번호_매칭_개수_계산_테스트() {
        // given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 8, 9);
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        int expected = 4;

        // when & then
        int actual = lottoTicket.countMatchedNumbers(winningNumbers);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 보너스_번호가_포함되지_않았을_때_매칭_테스트() {
        // given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when & then
        boolean actual = lottoTicket.hasBonusNumber(bonusNumber);
        Assertions.assertThat(actual).isFalse();
    }

    @Test
    void 로또_2등_테스트() {
        // given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 9);
        int bonusNumber = 6;

        // when
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        int countMatchedNumbers = lottoTicket.countMatchedNumbers(winningNumbers);
        boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);
        LottoPrize actual = LottoPrize.value(countMatchedNumbers, hasBonusNumber);
        LottoPrize expected = LottoPrize.SECOND;

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 당첨_통계_계산_테스트() {
        //given
        LottoTicket fifth = new LottoTicket(List.of(1, 2, 3, 43, 44, 45));
        LottoTicket fourth = new LottoTicket(List.of(1, 2, 3, 4, 44, 45));
        LottoTicket second = new LottoTicket(List.of(1, 2, 3, 4, 5, 7));
        LottoTicket nothing = new LottoTicket(List.of(31, 32, 33, 34, 35, 36));
        List<LottoTicket> lottoTickets = List.of(fifth, fourth, second, nothing);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, winningNumbers,
                bonusNumber);
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.getPrizeCounter();

        // then
        Assertions.assertThat(prizeCounter.get(LottoPrize.FIFTH)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.FOURTH)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.SECOND)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.NOTHING)).isEqualTo(1);
    }

    @Test
    void 수익률_계산_테스트() {
        //given
        List<Integer> integers = new java.util.ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(43);
        integers.add(44);
        integers.add(45);
        LottoTicket fifth = new LottoTicket(
                integers);
        LottoTicket fourth = new LottoTicket(List.of(1, 2, 3, 4, 44, 45));
        LottoTicket second = new LottoTicket(List.of(1, 2, 3, 4, 5, 7));
        LottoTicket nothing = new LottoTicket(List.of(31, 32, 33, 34, 35, 36));
        List<LottoTicket> lottoTickets = List.of(fifth, fourth, second, nothing);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, winningNumbers,
                bonusNumber);
        Profit profit = statisticsService.calculateProfit(winningStatistics, lottoTickets.size());

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(7513.75);
    }
}
