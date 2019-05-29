package lotto.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    public void money_생성_확인() {
        assertThat(new Money(1000)).isEqualTo(new Money(1000));
    }

    @Test
    public void money_구매가능금액_초과_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(100001);
        });
    }

    @Test
    public void money_구매가능금액_미달_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(999);
        });
    }

    @Test
    public void money_1000단위_금액_입력_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(1001);
        });
    }
}
