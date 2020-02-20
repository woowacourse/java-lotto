package lotto.domain.number;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또넘버생성() {
        LottoNumber lottoNumber = LottoNumber.of(10);
        assertThat(lottoNumber).isInstanceOf(LottoNumber.class);
        assertThat(lottoNumber.getNumber()).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @SuppressWarnings("NonAsciiCharacters")
    void 범위를_벗어난_로또_넘버_생성(int outOfBoundNumber) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.of(outOfBoundNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}