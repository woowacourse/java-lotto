package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자가 아닌 경우")
    void noNumberInput() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Number("abc"));
    }

    @Test
    @DisplayName("정상적인 숫자 생성")
    void normalInput() {
        Number number = new Number(1);
        assertThat(number.equals(1)).isTrue();

        Number numberByString = new Number("1");
        assertThat(numberByString.equals(1)).isTrue();
    }
}
