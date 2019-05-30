package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseInformationTest {
    @Test
    void 최대_구매가능한_로또_개수보다_수동_구매_로또의_개수가_많은_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                new PurchaseInformation(1000, 2));
    }

    @Test
    void 돈이_부족한_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                new PurchaseInformation(999, 1));
    }

    @Test
    void 최대_구매_가능_금액을_넘어간_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                new PurchaseInformation(1_000_000_001, 1));
    }
}
