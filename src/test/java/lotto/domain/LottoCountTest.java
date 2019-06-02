package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoCountTest {

    @Test
    void 전체_로또_개수보다_큰_수동_로또_개수를_입력한_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
           new LottoCount(new Money(5_000), 6);
        });
    }

    @Test
    void 수동_로또_개수() {
        LottoCount lottoCount = new LottoCount(new Money(10_000), 5);
        assertThat(5).isEqualTo(lottoCount.getManualCount());
    }

    @Test
    void 자동_로또_개수() {
        LottoCount lottoCount = new LottoCount(new Money(10_000), 4);
        assertThat(6).isEqualTo(lottoCount.getAutoCount());
    }
}
