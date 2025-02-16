package lotto.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    @DisplayName("로또 번호가 숫자가 아닌 예외")
    @Test
    public void lottoNumber() {
        String lottoNumbersInput = "일, 이, 삼, 사, 오, 육";
        LottoService lottoService = new LottoService();

        assertThatThrownBy(() -> lottoService.createLotto(lottoNumbersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
