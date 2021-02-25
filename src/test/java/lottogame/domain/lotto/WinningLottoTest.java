package lottogame.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    @Test
    void 객체_생성() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2),
                LottoNumber.of(3), LottoNumber.of(4),
                LottoNumber.of(5), LottoNumber.of(6)));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.of("7"));
        assertThat(winningLotto).isEqualTo(new WinningLotto(lotto, LottoNumber.of("7")));
    }

    @Test
    void 중복_체크() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2),
                LottoNumber.of(3), LottoNumber.of(4),
                LottoNumber.of(6), LottoNumber.of(6))), LottoNumber.of("7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호는 서로 달라야합니다.");

        assertThatThrownBy(() -> new WinningLotto(new Lotto(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2),
                LottoNumber.of(3), LottoNumber.of(4),
                LottoNumber.of(5), LottoNumber.of(6))), LottoNumber.of("6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스번호는 로또번호와 달라야 합니다.");
    }
}
