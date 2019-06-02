package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseInformationTest {
    @Test
    void 수동_로또의_개수보다_번호가_적게_입력된_경우() {
        PurchaseInformation purchaseInformation =
                new PurchaseInformation(new LottoCount(2, new Money(5000)));
        purchaseInformation.addManualLottoNumbers(LottoNumbersGenerator.getLottoNumbers());

        assertThat(purchaseInformation.isValidPurchaseInformation()).isFalse();
    }

    @Test
    void 수동_로또의_개수보다_번호가_많이_입력된_경우() {
        PurchaseInformation purchaseInformation =
                new PurchaseInformation(new LottoCount(2, new Money(5000)));
        purchaseInformation.addManualLottoNumbers(LottoNumbersGenerator.getLottoNumbers());
        purchaseInformation.addManualLottoNumbers(LottoNumbersGenerator.getLottoNumbers());
        purchaseInformation.addManualLottoNumbers(LottoNumbersGenerator.getLottoNumbers());

        assertThat(purchaseInformation.isValidPurchaseInformation()).isFalse();
    }
}
