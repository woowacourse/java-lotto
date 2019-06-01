package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoNoTest {
    @Test
    void 로또_번호_생성() {
        assertThat(LottNo.of(1)).isEqualTo(LottNo.of(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_번호_생성_예외(int no) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoNo.of(no);
        }).withMessage("로또 번호는 1 ~ 45 범위 입니다.");
    }
}