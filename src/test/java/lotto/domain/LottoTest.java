package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @Test
    void 로또_생성_검증() {
        //given
        String inputLotto = "1, 2, 3, 4, 5, 6";

        //when & then
        Assertions.assertDoesNotThrow(() -> new Lotto(inputLotto));
    }

    @Test
    void 랜덤_로또_생성_검증() {
        //given
        Lotto randomLotto = new Lotto(new FixedRandomLottoGenerator());
        Lotto fixedLotto = new Lotto("1, 2, 3, 4, 5, 6");
        int expectedMatchCount = 6;

        //when & then
        assertThat(randomLotto.getMatchCount(fixedLotto))
                .isEqualTo(expectedMatchCount);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3, 4, 5",
            "1, 2, 3, 4, 5, 6, 7"
    })
    void 로또_숫자_개수_오류(String invalidLotto) {
        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_변환_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5번, 6";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_범위_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5, 46";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_중복_오류() {
        //given
        String invalidLotto = "1, 2, 3, 4, 5, 5";

        //when & then
        assertThatThrownBy(() -> new Lotto(invalidLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_매치_수_검증() {
        //given
        Lotto winningLotto = new Lotto("1, 2, 3, 4, 5, 6");
        Lotto userLotto = new Lotto("1, 2, 3, 10, 11 ,12");
        int expectedMatchCount = 3;

        //when & then
        assertThat(userLotto.getMatchCount(winningLotto))
                .isEqualTo(expectedMatchCount);
    }

    @Test
    void 보너스_번호_매칭_확인() {
        //given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        int bonusNumber = 3;

        //when & then
        assertThat(lotto.containsNumber(bonusNumber)).isEqualTo(true);
    }

    @Test
    void 보너스_번호_매칭_실패() {
        //given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        int bonusNumber = 7;

        //when & then
        assertThat(lotto.containsNumber(bonusNumber)).isEqualTo(false);
    }
}
