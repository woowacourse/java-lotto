package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.NumbersGenerator;
import utils.RandomNumbersGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoStoreTest {

    private NumbersGenerator numbersGenerator = new RandomNumbersGenerator();

    @DisplayName("구입 금액 만큼 로또 구매를 성공한다")
    @Test
    void issueLottosTest() {
        //given
        final PurchaseAmount purchaseAmount1 = new PurchaseAmount(11000);
        final PurchaseAmount purchaseAmount2 = new PurchaseAmount(1000);

        //when
        final LottoStore lottoStore1 = new LottoStore(numbersGenerator, purchaseAmount1);
        final LottoStore lottoStore2 = new LottoStore(numbersGenerator, purchaseAmount2);

        //then
        assertAll(
                () -> assertThat(lottoStore1.issueLottos()).size().isEqualTo(11),
                () -> assertThat(lottoStore2.issueLottos()).size().isEqualTo(1)
        );
    }
}
