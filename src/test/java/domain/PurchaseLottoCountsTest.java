package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PurchaseLottoCountsTest {

    @DisplayName("수동 구매 개수가 음수인 경우 예외 발생")
    @Test
    void 수동_구매_개수_음수() {
        /* given */
        int manualCount = -1;
        Amount amount = new Amount(1000);

        /* when */
        assertThatThrownBy(() -> new PurchaseLottoCounts(manualCount, amount))
                /* then */
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동 구매 개수가 음수인 경우 예외 발생")
    @Test
    void 자동_구매_개수_음수() {

        /* given */
        int manualCount = 3;
        Amount amount = new Amount(2000);

        /* when */
        assertThatThrownBy(() -> new PurchaseLottoCounts(manualCount, amount))
                /* then */
                .isInstanceOf(IllegalArgumentException.class);
    }
}
