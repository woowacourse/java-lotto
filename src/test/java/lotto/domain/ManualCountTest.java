package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.ManualCountBoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ManualCountTest {

    @Test
    void factory_method_throw_exception() {
        assertThrows(ManualCountBoundException.class, () -> {
            ManualCount.is(5, new TotalCount(PurchaseAmount.is("1000")));
        });
    }

    @Test
    void getAutoCount() {
        TotalCount totalCount = new TotalCount(PurchaseAmount.is("7000"));
        assertThat(ManualCount.is(5, totalCount).getAutoCount(totalCount)).isEqualTo(new AutoCount(2));
    }
}