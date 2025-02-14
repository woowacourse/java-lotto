package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또_생성_검증() {
        //given
        String inputLotto = "1, 2, 3, 4, 5, 6";

        //when & then
        Assertions.assertDoesNotThrow(() -> new Lotto(inputLotto));
    }

    @Test
    void 로또_숫자_개수_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_LENGTH_ERROR.getMessage());
    }

    @Test
    void 로또_숫자_변환_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5번, 6";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_FORMAT_ERROR.getMessage());
    }

    @Test
    void 로또_숫자_범위_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5, 46";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.RANGE_ERROR.getMessage());
    }

    @Test
    void 로또_숫자_중복_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5, 5";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
    }

    @Test
    void 로또_매치_수_검증() {
        //given
        Lotto winningLotto = new Lotto("1, 2, 3, 4, 5, 6");
        Lotto userLotto = new Lotto("1, 2, 3, 10, 11 ,12");

        //when & then
        assertThat(userLotto.match(winningLotto)).isEqualTo(3);
    }

    @Test
    void 보너스_번호_매칭_확인() {
        //given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        int bonusNumber = 3;

        //when & then
        assertThat(lotto.checkBonusNumberMatch(bonusNumber)).isEqualTo(true);
    }

    @Test
    void 보너스_번호_매칭_실패() {
        //given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        int bonusNumber = 7;

        //when & then
        assertThat(lotto.checkBonusNumberMatch(bonusNumber)).isEqualTo(false);
    }
}