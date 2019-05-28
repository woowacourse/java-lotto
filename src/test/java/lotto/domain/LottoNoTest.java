package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNoTest {

    @Test
    void 생성_테스트() {
        assertThat(LottoNo.from(13)).isEqualTo(LottoNo.from(13));
    }

    @Test
    void 속성_일치_테스트() {
        assertThat(13).isEqualTo(LottoNo.from(13).value());
    }

    @Test
    void 유효값_테스트() {
        assertThrows(IllegalArgumentException.class, () -> LottoNo.from(47));
    }
}
