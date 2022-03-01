package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("로또 숫자 생성 테스트")
    @Test
    void lottoNumberTest() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.equals(new LottoNumber(1)));
    }

    @DisplayName("로또 숫자 생성 범위 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 46})
    void lottoNumberRangeTest(int lottoNumber) {
        assertThatThrownBy(() ->
                new LottoNumber(lottoNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
