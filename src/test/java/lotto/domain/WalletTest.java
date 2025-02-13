package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("toString 가공 테스트")
    @Test
    void testToString() {
        Amount amount = new Amount(5000);

        Wallet wallet = new Wallet(amount);

        System.out.println(wallet);
    }

}