package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("지갑 객체 생성 성공")
    @Test
    void create() {
        assertThatCode(() -> new Wallet(5000)).doesNotThrowAnyException();
    }

    @DisplayName("지갑 객체 생성 실패 : 음의 정수 입력")
    @Test
    void negative_amount_fail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Wallet(-100))
                .withMessageContaining("음의 정수");
    }

    @DisplayName("구매 성공, 보유 자산 감소")
    @Test
    void pay_successful() {
        Wallet wallet = new Wallet(10000);
        assertTrue(wallet.pay(1000));
        assertThat(wallet.getAmount()).isEqualTo(9000);
    }

    @DisplayName("구매 실패, 보유 자산 유지")
    @Test
    void pay_fail() {
        Wallet wallet = new Wallet(1000);
        assertFalse(wallet.pay(2000));
        assertThat(wallet.getAmount()).isEqualTo(1000);
    }
}