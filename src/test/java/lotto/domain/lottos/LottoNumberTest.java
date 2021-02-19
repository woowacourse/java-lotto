package lotto.domain.lottos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("숫자를 생성한다.")
    public void createNumberTest() {
        LottoNumber number = new LottoNumber(5);

        assertThat(number).isEqualTo(new LottoNumber(5));
    }

    @Test
    @DisplayName("로또 숫자는 1~45 사이의 숫자여야한다.")
    public void validateNumberTest() {
        assertThatThrownBy(() -> {
            new LottoNumber(46);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
