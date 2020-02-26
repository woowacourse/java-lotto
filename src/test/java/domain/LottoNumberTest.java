package domain;

import domain.numberscontainer.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또 숫자 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자 생성 예외처리")
    void lottoNumberConstructorTest() {
        assertThatThrownBy(() -> LottoNumber.getLottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("46이(가) 입력되었습니다. 1부터 45까지의 숫자를 입력해주세요.");
    }
}