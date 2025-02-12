import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("숫자6개를 갖는다")
    void 숫자6개를갖는다() {
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5);

        assertThatCode(() -> new Lotto(validNumbers))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}