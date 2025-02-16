package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    @DisplayName("당첨 로또 생성 성공")
    @Test
    void winningLottoCreation() {

        final Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 7;

        assertThatCode(() -> WinningLotto.of(lotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호와 당첨 번호 중복 시 예외 발생")
    @Test
    void duplicateBonusNumberCreationThrowException() {

        final Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 4;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("")
    @Test
    void calculatePrizesTest() {
        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        Lotto lotto1 = Lotto.of(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto lotto2 = Lotto.of(List.of(1, 2, 3, 4, 5, 7)); // 2둥
        Lotto lotto3 = Lotto.of(List.of(1, 2, 3, 4, 5, 8)); // 3둥
        Lotto lotto4 = Lotto.of(List.of(1, 2, 3, 4, 9, 10)); // 4둥
        Lotto lotto5 = Lotto.of(List.of(11, 12, 13, 4, 5, 6)); // 5둥
        Lotto lotto6 = Lotto.of(List.of(11, 12, 13, 14, 5, 6)); // 6둥

        List<Lotto> myLotto = List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
        Lottos lottos = Lottos.of(myLotto);

        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        assertThat(prizes).isEqualTo(Arrays.stream(Prize.values()).toList());
    }
}