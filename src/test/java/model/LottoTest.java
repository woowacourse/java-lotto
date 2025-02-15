package model;

import static model.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static model.ExceptionMessage.INVALID_LOTTO_RANGE;
import static model.ExceptionMessage.INVALID_LOTTO_TYPE;
import static model.ExceptionMessage.LOTTO_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("되는 로또")
    void validLotto() {
        Lotto lotto = Lotto.of("1, 2, 3, 4, 5, 6");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("null이 들어왔을 때 예외 처리된다.")
    void inputWithNull() {
        assertThatThrownBy(() -> Lotto.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("빈 문자열이 들어왔을 때 예외 처리된다.")
    void inputWithBlank() {
        assertThatThrownBy(() -> Lotto.of(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("중복된 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithDuplicate() {
        assertThatThrownBy(() -> Lotto.of("1, 1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LOTTO_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("문자가 들어왔을 때 예외 처리된다.")
    void inputWithCharacter() {
        assertThatThrownBy(() -> Lotto.of("1, ㅁ, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_TYPE.getMessage());
    }

    @Test
    @DisplayName("1 미만이거나 45 초과 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithInvalidRange() {
        assertThatThrownBy(() -> Lotto.of("48, 1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_RANGE.getMessage(1, 45));
    }
}
