package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자가 아닌 경우")
    void inputNoNumber() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Number.valueOf("abc"))
            .withMessageContaining("입력이 숫자가 아니거나");
    }

    @Test
    @DisplayName("Integer 범위 밖인 경우")
    void inputOutOfInteger() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Number.valueOf("9876543211324"))
            .withMessageContaining("범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("정상적인 숫자 생성")
    void inputNormal() {
        Number number = Number.valueOf(1);
        assertThat(number.toInt()).isEqualTo(1);

        number = Number.valueOf("1");
        assertThat(number.toInt()).isEqualTo(1);
    }
}