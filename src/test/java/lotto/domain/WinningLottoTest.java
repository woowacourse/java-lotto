package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import lotto.constant.ErrorMessage;
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
                .hasMessage(ErrorMessage.BONUS_NUMBER_FORMAT_ERROR.getMessage());
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
                .hasMessage(ErrorMessage.RANGE_ERROR.getMessage());
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
                .hasMessage(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
    }

}