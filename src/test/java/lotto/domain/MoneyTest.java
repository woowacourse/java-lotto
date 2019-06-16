package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    void 올바른_돈_검증() {
        Assertions.assertThat(new Money(1000).getMoney()).isEqualTo(1000);
    }

    @Test
    void 음의_돈_검증() {
        assertThrows(InvalidMoneyException.class, () -> new Money(-1000));
    }

    @Test
    void 천원_단위가_아닌_돈_검증() {
        assertThrows(InvalidMoneyException.class, () -> new Money(1));
    }


}
