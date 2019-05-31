package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseInformationTest {
    @Test
    void 최대_구매가능한_로또_개수보다_수동_구매_로또의_개수가_많은_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                new PurchaseInformation(new Money(1000), 2));
    }

    @Test
    void 영미만의_수동_구매_로또_개수() {
        assertThrows(IllegalArgumentException.class, () ->
                new PurchaseInformation(new Money(1000), -1));
    }
}
