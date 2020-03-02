package lotto.domain.lottoTicket;

import lotto.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 범위 실패 확인")
    void initFailTest() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(InvalidLottoNumberException.class);
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(InvalidLottoNumberException.class);
    }

    @Test
    @DisplayName("로또 숫자 범위 성공 확인")
    void initTest() {
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }
}
