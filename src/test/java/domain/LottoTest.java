package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("유효한 로또 번호를 발급을 확인한다.")
    @Test
    void input_lottoNumbers_valid() {
        assertDoesNotThrow(() -> new Lotto("1, 2, 3, 4, 5, 6"));
    }

    @DisplayName("중복된 로또 번호를 발급할시 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_duplicated() {
        assertThatThrownBy(() -> new Lotto("1, 1, 3, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지난 주 당첨 번호 입력시 숫자가 아닌 경우 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_format() {
        assertThatThrownBy(() -> new Lotto("1, a, 3, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("발급 로또와 당첨 로또를 비교한 뒤 당첨 갯수 반환을 확인한다.")
    @Test
    void compare_LottoNumbers() {
        final Lotto lotto = new Lotto("1,2,3,4,5,6");
        final Lotto winNumbers = new Lotto("1,2,3,4,5,7");

        final int winCount = lotto.compare(winNumbers);

        assertThat(winCount).isEqualTo(5);
    }

    @DisplayName("발급 로또와 보너스 번호를 비교하여 포함여부를 확인한다.")
    @Test
    void compare_bonusNumbers() {
        final Lotto lotto = new Lotto("1,2,3,4,5,6");
        final LottoNumber bonusNumber = new LottoNumber("6");

        final boolean hasBonus = lotto.isContainNumber(bonusNumber);

        assertThat(hasBonus).isTrue();
    }
}
