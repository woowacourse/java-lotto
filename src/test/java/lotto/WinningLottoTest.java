package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 던진다")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @ParameterizedTest
    void 당첨_번호와_보너스_번호가_중복되면_예외를_던진다(int bonusNumber) {
        //given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when
        //then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }

    @DisplayName("여러 로또의 당첨 통계를 계산할 수 있다")
    @CsvSource(value = {"FIRST:1", "SECOND:0", "THIRD:1", "FOURTH:0", "FIFTH:0", "NONE:1"}, delimiterString = ":")
    @ParameterizedTest
    void 여러_로또의_당첨_통계를_계산할_수_있다(Rank rank, int expected) {
        //given
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6))); // FIRST
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 8))); // THIRD
        lottos.add(new Lotto(List.of(7, 8, 9, 10, 11, 12))); // NONE

        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        //when
        WinningStatistics result = winningLotto.calculateStatistics(lottos);

        //then
        assertThat(result.getRankCount(rank)).isEqualTo(expected);
    }
}
