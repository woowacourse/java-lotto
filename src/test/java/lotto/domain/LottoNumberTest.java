package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoNumberTest {
    @Test
    void 생성자_생성() {
        assertThat(LottoNumber.getNumber(0)).isEqualTo(LottoNumber.getNumber(0));
    }

    @Test
    void 생성자_오류_범위를_벗어남() {
        assertThatThrownBy(() -> LottoNumber.getNumber(0)).isInstanceOf(NullPointerException.class);
        // 김재윤 010 4633 5850
    }
}
