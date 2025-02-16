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

    public static Stream<Arguments> calculateCountMatchedNumbersCases() {
        return Stream.of(
                Arguments.arguments(List.of(11, 12, 13, 14, 15, 16), List.of(1, 2, 3, 4, 5, 6), 0),
                Arguments.arguments(List.of(1, 12, 13, 14, 15, 16), List.of(1, 2, 3, 4, 5, 6), 1),
                Arguments.arguments(List.of(1, 2, 13, 14, 15, 16), List.of(1, 2, 3, 4, 5, 6), 2),
                Arguments.arguments(List.of(1, 2, 3, 14, 15, 16), List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.arguments(List.of(1, 2, 3, 4, 15, 16), List.of(1, 2, 3, 4, 5, 6), 4),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 16), List.of(1, 2, 3, 4, 5, 6), 5),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    public static Stream<Arguments> bonusNumberNotMatchedCases() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 7, false),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 8, false),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 9, false),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 10, false)

        );
    }

    public static Stream<Arguments> bonusNumberMatchedCases() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 1, true),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 2, true),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 3, true)
        );
    }

    @DisplayName("당첨번호 매칭 개수가 올바르게 계산되는지 테스트")
    @ParameterizedTest
    @MethodSource("calculateCountMatchedNumbersCases")
    void 당첨번호_매칭_개수_테스트(List<Integer> lottoNumbers, List<Integer> winningNumbers, int expected) {
        // given
        LottoTicket lottoTicket = LottoTicket.createLottoTicket(lottoNumbers);
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream().map(LottoNumber::new).toList();

        // when
        int actual = lottoTicket.countMatchedLottoNumbers(winningLottoNumbers);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("보너스 번호가 매칭되지 않는 경우 false를 반환")
    @ParameterizedTest
    @MethodSource("bonusNumberNotMatchedCases")
    void 보너스_번호가_매칭되지_않는_경우(List<Integer> lottoNumbers, int bonusNumber, boolean expected) {
        // given
        LottoTicket lottoTicket = LottoTicket.createLottoTicket(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        // when
        boolean actual = lottoTicket.containsLottoNumber(bonusLottoNumber);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("보너스 번호가 매칭되는 경우 true를 반환")
    @ParameterizedTest
    @MethodSource("bonusNumberMatchedCases")
    void 보너스_번호가_매칭되는_경우(List<Integer> lottoNumbers, int bonusNumber, boolean expected) {
        // given
        LottoTicket lottoTicket = LottoTicket.createLottoTicket(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        // when
        boolean actual = lottoTicket.containsLottoNumber(bonusLottoNumber);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주어진 로또티켓들로 당첨 통계를 올바르게 계산하는지 테스트")
    @Test
    void 당첨_통계_계산() {
        //given
        LottoTicket fifth = LottoTicket.createLottoTicket(List.of(1, 2, 3, 43, 44, 45));
        LottoTicket fourth = LottoTicket.createLottoTicket(List.of(1, 2, 3, 4, 44, 45));
        LottoTicket second = LottoTicket.createLottoTicket(List.of(1, 2, 3, 4, 5, 7));
        LottoTicket nothing = LottoTicket.createLottoTicket(List.of(31, 32, 33, 34, 35, 36));
        LottoTickets lottoTickets = new LottoTickets(List.of(fifth, fourth, second, nothing));
        List<LottoNumber> winningLottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        LottoNumber bonusNumber = new LottoNumber(7);
        DrawResult drawResult = new DrawResult(winningLottoNumbers, bonusNumber);

        // when
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.getPrizeCounter();

        // then
        Assertions.assertThat(prizeCounter.get(LottoPrize.FIFTH)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.FOURTH)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.SECOND)).isEqualTo(1);
        Assertions.assertThat(prizeCounter.get(LottoPrize.NOTHING)).isEqualTo(1);
    }

    @DisplayName("수익률 계산 테스트")
    @Test
    void 수익률_계산() {
        //given
        LottoTicket fifth = LottoTicket.createLottoTicket(List.of(1, 2, 3, 43, 44, 45));
        LottoTicket fourth = LottoTicket.createLottoTicket(List.of(1, 2, 3, 4, 44, 45));
        LottoTicket second = LottoTicket.createLottoTicket(List.of(1, 2, 3, 4, 5, 7));
        LottoTicket nothing = LottoTicket.createLottoTicket(List.of(31, 32, 33, 34, 35, 36));
        LottoTickets lottoTickets = new LottoTickets(List.of(fifth, fourth, second, nothing));
        List<LottoNumber> winningLottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        LottoNumber bonusNumber = new LottoNumber(7);
        DrawResult drawResult = new DrawResult(winningLottoNumbers, bonusNumber);

        // when
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        double profit = statisticsService.calculateProfit(winningStatistics);

        // then
        Assertions.assertThat(profit).isEqualTo(7513.75);
    }
}
