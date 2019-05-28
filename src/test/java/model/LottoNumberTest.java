package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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