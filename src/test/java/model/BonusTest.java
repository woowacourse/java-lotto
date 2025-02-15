package model;

import static model.ExceptionMessage.BONUS_DUPLICATE;
import static model.ExceptionMessage.INVALID_BONUS_RANGE;
import static model.ExceptionMessage.INVALID_BONUS_TYPE;
import static model.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    private Lotto lotto;
    @BeforeEach
    void beforeEach() {
        lotto = Lotto.of("1, 2, 3, 4, 5, 6");
    }

    @Test
    @DisplayName("되는 보너스")
    void validBonus() {
        String bonusNumber = "7";
        Integer expected = 7;
        Bonus bonus = Bonus.of(bonusNumber, lotto);

        assertThat(bonus.getNumber()).isEqualTo(expected);
    }

    @Test
    @DisplayName("null이 들어왔을 때 예외 처리된다.")
    void inputWithNull() {
        assertThatThrownBy(() -> Bonus.of(null, lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("빈 문자열이 들어왔을 때 예외 처리된다.")
    void inputWithBlank() {
        assertThatThrownBy(() -> Bonus.of(" ", lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("로또와 중복된 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithDuplicate() {
        assertThatThrownBy(() -> Bonus.of("1", lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BONUS_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("문자가 들어왔을 때 예외 처리된다.")
    void inputWithCharacter() {
        assertThatThrownBy(() -> Bonus.of("ㅁ", lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_BONUS_TYPE.getMessage());
    }

    @Test
    @DisplayName("1 미만이거나 45 초과 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithInvalidRange() {
        assertThatThrownBy(() -> Bonus.of("46", lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_BONUS_RANGE.getMessage(1, 45));
    }

    @Test
    @DisplayName("숫자의 갯수가 여러개 일 때 예외 처리된다.")
    void inputWithInvalidCount() {
        assertThatThrownBy(() -> Bonus.of("7, 8", lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_BONUS_TYPE.getMessage());
    }
}
