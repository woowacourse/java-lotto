package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 돈이_부족한_경우() {
        assertThrows(IllegalArgumentException.class, () -> new Money(999));
    }

    @Test
    void 최대_구매_가능_금액을_넘어간_경우() {
        assertThrows(IllegalArgumentException.class, () -> new Money(1_000_000_001));
    }

    @Test
    void 최대_구매_가능_개수_확인() {
        assertThat(new Money(1999).calculateMaxNumberOfPurchase()).isEqualTo(1);
    }
}
