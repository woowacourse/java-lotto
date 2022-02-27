package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        assertThatCode(Lotto::generate)
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 44, 45));

        int actual = lotto.getMatchScore(winningNumbers);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 당첨 여부를 확인한다")
    void matchBonusNumberTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        boolean actual = lotto.isMatchBonusNumber(bonusNumber);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("로또 번호 개수가 6개 미만인 경우 예외 처리")
    void validateNumberOfLottoUnder6Test() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("로또 번호 개수가 6개 초과인 경우 예외 처리")
    void validateNumberOfLottoOver6Test() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("볼 번호의 범위가 1~45가 아닌 경우 예외 처리")
    void validateRangeLottoTest() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(1, 2, 3, 4, 55, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("볼 번호가 1~45 범위 내에 해당하지 않습니다.");
    }

    @Test
    @DisplayName("로또 번호 중 중복된 값이 입력될 경우 예외 처리")
    void validateDuplicationLottoTest() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(1, 2, 3, 3, 4, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호에 중복이 존재합니다.");
    }

    @Test
    @DisplayName("Lotto가 LottoNumber를 포함하는지 확인한다")
    void matchTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(6);

        assertThat(lotto.isMatchNumber(lottoNumber)).isTrue();
    }
}
