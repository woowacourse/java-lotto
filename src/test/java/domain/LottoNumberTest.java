package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {

    @Test
    @DisplayName("유효 범위 내의 로또 숫자 생성 테스트")
    void lottoNumber() {
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }

}
