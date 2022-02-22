package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    void 로또_구매_개수_판별() {
        Lotto lotto = new Lotto(14000);
        assertThat(lotto.getTicketCount()).isEqualTo(14);
    }

    @Test
    void 로또_투입금액_0() {
        assertThatThrownBy(() -> new Lotto(0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_투입금액_음수() {
        assertThatThrownBy(() -> new Lotto(-1000))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_투입금액_천원단위로_안나누어떨어질때() {
        assertThatThrownBy(() -> new Lotto(100))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
