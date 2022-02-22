package lotto;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 6자리 숫자가 전달되지 않으면 예외가 발생한다.")
    void throwExceptionWhenNotSixSizeNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호는 6자리 숫자로 생성된다.")
    void createSixSizeNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertThat(new Lotto(numbers)).isNotNull();
    }

}