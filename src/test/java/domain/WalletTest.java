package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("지갑 객체 생성 성공")
    @Test
    void create() {
        assertThatCode(() -> new Wallet(5000, 4)).doesNotThrowAnyException();
    }

    @DisplayName("지갑 객체 생성 실패 : 음의 정수 입력")
    @Test
    void negative_amount_fail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Wallet(-1000, 1))
                .withMessageContaining("음의 정수");
    }

    @DisplayName("지갑 객체 생성 실패 : 수동 로또 개수의 금액 초과")
    @Test
    void more_manualCount_fail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Wallet(2000, 3))
                .withMessageContaining("더 많은");
    }
}