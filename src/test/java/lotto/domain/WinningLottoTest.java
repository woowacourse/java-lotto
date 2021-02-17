package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.util.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

  private Lotto lotto;
  private LottoNumber bonusNumber;

  @BeforeEach
  void setUp() {
    lotto = LottoGenerator.generate(1, 2, 3, 4, 5, 6);
    bonusNumber = LottoNumber.of(7);
  }

  @Test
  void checkDuplicateWinningNumberWithBonusNumber() {
    assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.of(6))).isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,33:THIRD"}, delimiter = ':')
  void checkRankWithInputLotto(String numbers, LottoRank expectedRank) {
    WinningLotto winningLotto = new WinningLotto(lotto, this.bonusNumber);
    int[] lottoNumbers = Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).toArray();
    LottoRank rank = winningLotto.matchRank(LottoGenerator.generate(lottoNumbers));
    assertThat(rank).isEqualTo(expectedRank);
  }
}