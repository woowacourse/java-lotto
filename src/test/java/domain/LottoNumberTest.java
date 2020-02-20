package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 숫자 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 생성 예외처리")
    void lottoNumberConstructorTest() {
        assertThat(LottoNumber.getLottoNumber(46)).isEqualTo(LottoNumber.ERROR);
    }
}
