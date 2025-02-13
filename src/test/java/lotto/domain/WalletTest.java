package lotto.domain;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("로또 가격에 맞춰 구매가 된다. (천원)")
    @Test
    void test_matchCount() {
        // matchCount(Lotto matchLotto, int bonus)
        Amount amount = new Amount(5000);

        Lotto matchLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 45;

        Wallet wallet = new Wallet(amount);

        wallet.matchCount(matchLotto, bonus);
    }

}