package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    void 로또_숫자_생성_테스트() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.equals(new LottoNumber(1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,46})
    void 로또_숫자_생성_테스트_범위(int lottoNumber) {
        assertThatThrownBy(() ->
                new LottoNumber(lottoNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
