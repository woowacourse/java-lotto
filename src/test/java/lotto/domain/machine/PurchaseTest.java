package lotto.domain.machine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseTest {
    @Test
    public void 자동_갯수() {
        assertThat(Purchase.of(5, 3, Arrays.asList(ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)))).getAutoTicketQuantity()).isEqualTo(2);
    }

    @Test
    public void 수동_갯수() {
        assertThat(Purchase.of(5, 3, Arrays.asList(ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)))).getManualTicketQuantity()).isEqualTo(3);
    }

    @Test
    public void 수동_수동번호_갯수_불일치_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            Purchase.of(5, 3, Arrays.asList(ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6))));
        });
    }
}