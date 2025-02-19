package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningCaseTest {

  @ParameterizedTest
  @MethodSource("winningAmountAndWinningCaseTestData")
  @DisplayName("당첨_로또_수량으로_총_수익_금액_계산")
  void 당첨_로또_수량으로_총_수익_금액_계산(int amount, WinningCase winningCase, long earnMoney) {
    assertThat(winningCase.calculateEarnMoney(amount)).isEqualTo(earnMoney);
  }

  @ParameterizedTest
  @MethodSource("sameCountAndIsBonusTestData")
  @DisplayName("로또_일치_개수와_보너스볼_일치_여부에_따라_등수_반환")
  void 로또_일치_개수와_보너스볼_일치_여부에_따라_등수_반환(int sameCount, boolean isBoolean, WinningCase winningCase) {
    assertThat(WinningCase.getWinningCase(sameCount, isBoolean)).isEqualTo(winningCase);
  }

  static Stream<Arguments> sameCountAndIsBonusTestData() {
    return Stream.of(
        Arguments.of(0, false, WinningCase.ELSE),
        Arguments.of(3, false, WinningCase.THREE_SAME),
        Arguments.of(4, false, WinningCase.FOUR_SAME),
        Arguments.of(5, false, WinningCase.FIVE_SAME),
        Arguments.of(5, true, WinningCase.FIVE_BONUS_SAME),
        Arguments.of(6, false, WinningCase.SIX_SAME)
    );
  }

  static Stream<Arguments> winningAmountAndWinningCaseTestData() {
    return Stream.of(
        Arguments.of(3, WinningCase.ELSE, 0L),
        Arguments.of(3, WinningCase.THREE_SAME, 15_000L),
        Arguments.of(3, WinningCase.FOUR_SAME, 150_000L),
        Arguments.of(3, WinningCase.FIVE_SAME, 4_500_000L),
        Arguments.of(3, WinningCase.FIVE_BONUS_SAME, 90_000_000L),
        Arguments.of(3, WinningCase.SIX_SAME, 6_000_000_000L)
    );
  }
}
