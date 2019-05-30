package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @Test
    void initTestA() {
        assertThatThrownBy(() -> LottoNumber.of(355));
    }

    @Test
    void initTestB() {
        assertThatThrownBy(() -> LottoNumber.of(-2));
    }
}