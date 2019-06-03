package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseInformationTest {
    @Test
    void 수동_로또의_개수보다_번호가_적게_입력된_경우() {
        PurchaseInformation purchaseInformation =
                new PurchaseInformation(new LottoCount(2, new Money(5000)));
        purchaseInformation.addManualLottoNumbers("1,2,3,4,5,6");

        assertThrows(InvalidPurchaseInformationException.class, () ->
                purchaseInformation.checkValidPurchaseInformation());
    }

    @Test
    void 수동_로또의_개수보다_번호가_많이_입력된_경우() {
        PurchaseInformation purchaseInformation =
                new PurchaseInformation(new LottoCount(2, new Money(5000)));
        purchaseInformation.addManualLottoNumbers("1,2,3,4,5,6");
        purchaseInformation.addManualLottoNumbers("1,2,3,4,5,6");
        purchaseInformation.addManualLottoNumbers("1,2,3,4,5,6");

        assertThrows(InvalidPurchaseInformationException.class, () ->
                purchaseInformation.checkValidPurchaseInformation());
    }
}
