package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoNumberTest {

    @DisplayName("로또 번호 범위 밖의 숫자가 들어올 때 예외 테스트")
    @Test
    void testValidateNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumber(46));
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoNumber(0));
    }
}
