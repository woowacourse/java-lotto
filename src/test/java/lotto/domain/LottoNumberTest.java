package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("로또번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void create(int input) {
        LottoNumber lottoNumber = LottoNumber.valueOf(input);
        assertThat(lottoNumber).isEqualTo(LottoNumber.valueOf(input));
    }
}
