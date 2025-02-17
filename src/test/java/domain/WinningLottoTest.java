package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    @DisplayName("당첨 로또 생성 성공")
    @Test
    void winningLottoCreation() {

        final Lotto lotto = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        final int bonusNumber = 7;

        assertThatCode(() -> WinningLotto.of(lotto, LottoNumber.of(bonusNumber)))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호와 당첨 번호 중복 시 예외 발생")
    @Test
    void duplicateBonusNumberCreationThrowException() {

        final Lotto lotto = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        final int bonusNumber = 4;

        assertThatThrownBy(() -> WinningLotto.of(lotto, LottoNumber.of(bonusNumber)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("")
    @Test
    void calculatePrizesTest() {
        Lotto winningNumbers = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, LottoNumber.of(bonusNumber));

        Lotto lotto1 = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList()); // 1등
        Lotto lotto2 = Lotto.of(Stream.of(1, 2, 3, 4, 5, 7).map(LottoNumber::of).toList()); // 2둥
        Lotto lotto3 = Lotto.of(Stream.of(1, 2, 3, 4, 5, 8).map(LottoNumber::of).toList()); // 3둥
        Lotto lotto4 = Lotto.of(Stream.of(1, 2, 3, 4, 9, 10).map(LottoNumber::of).toList()); // 4둥
        Lotto lotto5 = Lotto.of(Stream.of(11, 12, 13, 4, 5, 6).map(LottoNumber::of).toList()); // 5둥
        Lotto lotto6 = Lotto.of(Stream.of(11, 12, 13, 14, 5, 6).map(LottoNumber::of).toList()); // 6둥

        List<Lotto> myLotto = List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
        Lottos lottos = Lottos.of(myLotto);

        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        assertThat(prizes).isEqualTo(Arrays.stream(Prize.values()).toList());
    }
}
