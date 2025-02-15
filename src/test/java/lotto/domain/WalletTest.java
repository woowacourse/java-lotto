package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("구매한 로또 목록이 요구 사항과 동일한 형태로 출력된다.")
    @Test
    void WalletToStringIsSatisfiedRequest() {
        Amount amount = new Amount(5000);

        Wallet wallet = new Wallet(amount);

        String output = wallet.toString();
        String[] lines = output.split("\n");
        assertThat(lines).hasSize(5);
    }

}
