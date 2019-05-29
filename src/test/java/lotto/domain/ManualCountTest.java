package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.ManualCountBoundException;
import lotto.domain.game.ManualCount;
import lotto.domain.game.Count;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ManualCountTest {

    @Test
    void factory_method_throw_exception() {
        assertThrows(ManualCountBoundException.class, () -> {
            ManualCount.is(5, new Count(PurchaseAmount.is("1000")));
        });
    }

    @Test
    void getAutoCount() {
        Count count = new Count(PurchaseAmount.is("7000"));
        assertThat(ManualCount.is(5, count).getAutoCount(count)).isEqualTo(new Count(2));
    }
}