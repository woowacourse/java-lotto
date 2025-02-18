package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @DisplayName("로또 번호가 숫자가 아닌 예외")
    @Test
    public void lottoNumber() {
        String lottoNumbersInput = "일, 이, 삼, 사, 오, 육";
        LottoFactory lottoFactory = new LottoFactory();

        assertThatThrownBy(() -> lottoFactory.createLotto(lottoNumbersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
