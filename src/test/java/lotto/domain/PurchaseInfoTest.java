package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseInfoTest {
    @DisplayName("구입정보 생성 테스트")
    @Test
    void createPurchaseInfoTest() {
        int numberOfManualLotto = 2;
        PurchaseInfo purchaseInfo = new PurchaseInfo(new Money(3000), new Money(1000), numberOfManualLotto);
        assertThat(purchaseInfo.numberOfManualLotto()).isEqualTo(numberOfManualLotto);
    }

    @DisplayName("남은 금액 테스트")
    @ParameterizedTest
    @CsvSource({"5000,1000,3000", "8000,1500,5000", "1500,250,1000"})
    void calculateLeftMoney(int purchaseMoney, int lottoPrice, int expectedMoneyAmount) {
        PurchaseInfo purchaseInfo = new PurchaseInfo(new Money(purchaseMoney), new Money(lottoPrice), 2);
        assertThat(purchaseInfo.calculateLeftMoney()).isEqualTo(new Money(expectedMoneyAmount));
    }

    @DisplayName("자동로또 개수 구하기 테스트")
    @ParameterizedTest
    @CsvSource({"20,10,10", "30,0,30", "25,15,10", "1,1,0"})
    void numberOfAutoNumberTest(int allLottoSize, int manualSize, int expectedAutoSize) {
        int lottoPrice = 1000;
        PurchaseInfo purchaseInfo = new PurchaseInfo(new Money(lottoPrice * allLottoSize), new Money(lottoPrice), manualSize);
        assertThat(purchaseInfo.numberOfAutoLotto()).isEqualTo(expectedAutoSize);
    }
}