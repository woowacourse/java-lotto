package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseLottoTest {
    @ParameterizedTest
    @DisplayName("로또 구매 매수 계산")
    @CsvSource(value = {"10,0","1000,1","2000,2","3333,3"})
    void purchaseTest(int money, int result) {
        assertThat(PurchaseLotto.purchase(money)).isEqualTo(result);
    }
}
