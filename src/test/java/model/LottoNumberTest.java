package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @Test
    void overflowTest() {
        assertThatThrownBy(() -> LottoNumber.of(355));
    }

    @Test
    void underflowTest() {
        assertThatThrownBy(() -> LottoNumber.of(-2));
    }
}