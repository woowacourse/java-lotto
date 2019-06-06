package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class WinningLottoTest {
    @Test
    void 보너스_번호_중복_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new WinningLotto(Lotto.of(new LottoManualGenerator(Arrays.asList(1, 2, 3, 4, 5, 6))),
                    LottoNo.of(3));
        }).withMessage("보너스 번호가 로또 번호에 포함됩니다.");
    }
}