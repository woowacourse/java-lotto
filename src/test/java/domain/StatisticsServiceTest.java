package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StatisticsServiceTest {
    private final StatisticsService statisticsService = new StatisticsService();

    public static Stream<Arguments> calculateCountMatchedNumbersTestCases() {
        return Stream.of(
                Arguments.arguments(new LottoTicket(List.of(11, 12, 13, 14, 15, 16)), List.of(1, 2, 3, 4, 5, 6), 0),
                Arguments.arguments(new LottoTicket(List.of(1, 12, 13, 14, 15, 16)), List.of(1, 2, 3, 4, 5, 6), 1),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 13, 14, 15, 16)), List.of(1, 2, 3, 4, 5, 6), 2),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 14, 15, 16)), List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 15, 16)), List.of(1, 2, 3, 4, 5, 6), 4),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 16)), List.of(1, 2, 3, 4, 5, 6), 5),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    public static Stream<Arguments> hasBonusNumberTestCases() {
        return Stream.of(
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 1, true),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 2, true),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 3, true),
                Arguments.arguments(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 7, false)
        );
    }

    @DisplayName("매칭된 번호 개수 계산이 올바르게 되는지 테스트")
    @ParameterizedTest
    @MethodSource("calculateCountMatchedNumbersTestCases")
    void 당첨번호_매칭_개수_계산_테스트(LottoTicket lottoTicket, List<Integer> winningNumbers, int expected) {
        // when
        int actual = lottoTicket.countMatchedNumbers(winningNumbers);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("보너스 번호 매칭 여부를 제대로 계산하는지 테스트")
    @ParameterizedTest
    @MethodSource("hasBonusNumberTestCases")
    void 보너스_번호_매칭_여부_계산_테스트(LottoTicket lottoTicket, int bonusNumber, boolean expected) {
        // when
        boolean actual = lottoTicket.hasBonusNumber(bonusNumber);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("당첨된 로또들로 통계 계산 테스트")
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

    @DisplayName("당첨된 로또에 대한 수익률 계산 테스트")
    @Test
    void 수익률_계산_테스트() {
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
        Profit profit = statisticsService.calculateProfit(winningStatistics);

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(7513.75);
    }
}
