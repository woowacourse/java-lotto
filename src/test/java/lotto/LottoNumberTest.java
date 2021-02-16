package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberTest {

    @Test
    @DisplayName("숫자를 생성한다.")
    public void createNumberTest() {
        LottoNumber number = new LottoNumber(5);
        assertThat(number).isEqualTo(new LottoNumber(5));
    }
}
