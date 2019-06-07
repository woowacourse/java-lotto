package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void create() {
        LottoNumber lottoNumber = LottoNumber.of(33);
        assertThat(lottoNumber).isEqualTo(LottoNumber.of(33));
    }

    @Test
    void invalidRange() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(-1));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(0));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(46));
        assertDoesNotThrow(() -> LottoNumber.of(45));
        assertDoesNotThrow(() -> LottoNumber.of(1));
    }
}
