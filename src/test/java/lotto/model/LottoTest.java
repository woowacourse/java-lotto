package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("4등 당첨 판정을 확인한다.")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 44, 45));

        Rank actual = lotto.match(winningNumbers, new LottoNumber(7));

        assertThat(actual).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("2등 당첨으로 보너스 번호 일치 여부를 확인한다")
    void matchBonusNumberTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45));
        LottoNumber bonusNumber = new LottoNumber(6);

        Rank actual = lotto.match(winningNumbers, bonusNumber);

        assertThat(actual).isEqualTo(Rank.SECOND);
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
}
