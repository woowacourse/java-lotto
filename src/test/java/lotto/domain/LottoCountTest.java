package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoCountTest {
    @Test
    void 수동_로또의_개수가_0미만인_경우() {
        assertThrows(InvalidPurchaseInformationException.class, () ->
                new LottoCount(-1, new Money(1000)));
    }

    @Test
    void 구입_금액이_부족한_경우() {
        assertThrows(InvalidPurchaseInformationException.class, () ->
                new LottoCount(2, new Money(1999)));
    }
}
