package lotto.domain.lottoTicket;

import lotto.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 숫자 범위 확인")
    void validateNumberScopeTest() {
        //실패
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(InvalidLottoNumberException.class);
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(InvalidLottoNumberException.class);

        //성공
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }
}
