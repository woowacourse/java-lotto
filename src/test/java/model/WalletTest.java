package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WalletTest {

    @ParameterizedTest
    @DisplayName("구매 금액에 맞는 로또 수량을 반환한다.")
    @CsvSource(value = {"0, 0", "1000,1", "3000, 3", "10000, 10"})
    void 구매_금액에_맞는_로또_수량을_반환한다(int input, int expected) {
        Wallet wallet = new Wallet(input);

        assertThat(wallet.getPurchasableQuantity()).isEqualTo(expected);
    }

}
