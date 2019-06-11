package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    public void 유효한_금액이_입력됐을_때() {
        assertThat(new Money(2000)).isExactlyInstanceOf(Money.class);
    }

    @Test
    public void 천원_이하의_금액이_입력됐을_때() {
        assertThatThrownBy(() -> {
            new Money(999);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 천원_단위가_아닌_금액이_입력됐을_때() {
        assertThatThrownBy(() -> {
            new Money(1001);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 십만원_이상의_금액이_입력됐을_때() {
        assertThatThrownBy(() -> {
            new Money(101000);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
