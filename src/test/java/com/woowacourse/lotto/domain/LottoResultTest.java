package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void valueOf() {
        assertThat(LottoResult.valueOf(5, false)).isEqualTo(LottoResult.THIRD);
        assertThat(LottoResult.valueOf(5, true)).isEqualTo(LottoResult.SECOND);
        assertThat(LottoResult.valueOf(6, true)).isEqualTo(LottoResult.FIRST);
        assertThat(LottoResult.valueOf(4, false)).isEqualTo(LottoResult.FOURTH);
        assertThat(LottoResult.valueOf(3, false)).isEqualTo(LottoResult.FIFTH);
        assertThat(LottoResult.valueOf(1, true)).isEqualTo(LottoResult.NONE);
    }
}
