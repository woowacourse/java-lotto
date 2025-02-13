package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 보너스_숫자_숫자_검증() {
        //given
        String winningLottoNumber = "1, 2, 3, 4, 5, 6";
        String bonusNumber = "1번";

        //when & then
        Assertions.assertThatThrownBy(() -> {
                    new WinningLotto(new Lotto(winningLottoNumber), bonusNumber);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 숫자는 숫자여야 합니다.");

    }

    @Test
    void 보너스_숫자_범위_검증() {
        //given
        String winningLottoNumber = "1, 2, 3, 4, 5, 6";
        String bonusNumber = "46";

        //when & then
        Assertions.assertThatThrownBy(() -> {
            new WinningLotto(new Lotto(winningLottoNumber), bonusNumber);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1과 45 사이의 수를 입력하세요.");

    }

    @Test
    void 보너스_숫자_중복_검증() {
        //given
        String winningLottoNumber = "1, 2, 3, 4, 5, 6";
        String bonusNumber = "1";

        //when & then
        Assertions.assertThatThrownBy(() -> {
                    new WinningLotto(new Lotto(winningLottoNumber), bonusNumber);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");

    }
}