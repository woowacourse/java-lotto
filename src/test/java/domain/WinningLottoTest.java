package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {

  @ParameterizedTest
  @MethodSource("buyLottosTestData")
  @DisplayName("전체_로또_당첨_결과_반환")
  void 전체_로또_당첨_결과_반환(List<Lotto> lottos) {
    WinningNumber winningNumber = new WinningNumber(List.of(1,2,3,4,5,6));
    BonusNumber bonusNumber = new BonusNumber(7);

    WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

    Map<WinningCase, Integer> winningCaseIntegerMap = winningLotto.winningCalculate(lottos);

    assertAll(
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.ELSE)).isEqualTo(1),
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.THREE_SAME)).isEqualTo(1),
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.FOUR_SAME)).isEqualTo(1),
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.FIVE_SAME)).isEqualTo(1),
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.FIVE_BONUS_SAME)).isEqualTo(1),
        () -> assertThat(winningCaseIntegerMap.get(WinningCase.SIX_SAME)).isEqualTo(1)
    );
  }

  static Stream<Arguments> buyLottosTestData() {
    return Stream.of(
        Arguments.of(
            List.of(
                new Lotto(List.of(10,12,13,14,15,16)),
                new Lotto(List.of(1,2,3,10,11,12)),
                new Lotto(List.of(1,2,3,4,15,16)),
                new Lotto(List.of(1,2,3,4,5,16)),
                new Lotto(List.of(1,2,3,4,5,7)),
                new Lotto(List.of(1,2,3,4,5,6))
                )
            )
        );
  }

}
