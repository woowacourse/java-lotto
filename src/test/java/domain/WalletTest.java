package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("지갑 객체 생성 성공")
    @Test
    void create() {
//        assertThatCode(() -> new User(5000)).doesNotThrowAnyException();
    }

    @DisplayName("지갑 객체 생성 실패: 음의 정수 입력")
    @Test
    void negative_amount_fail() {
//        assertThatIllegalArgumentException()
//                .isThrownBy(() -> new User(-100))
//                .withMessageContaining("음의 정수");
    }

    @DisplayName("구매 성공, 보유 자산 감소")
    @Test
    void pay_successful() {
//        User user = new User(10000);
//        assertThat(user.buyAutoLotto()).isEqualTo(10);
//        assertThat(user.getPayment()).isEqualTo(10000);
    }

    @DisplayName("구매 실패, 보유 자산 유지")
    @Test
    void pay_fail() {
//        User user = new User(900);
//        assertThat(user.buyAutoLotto()).isEqualTo(0);
//        assertThat(user.getPayment()).isEqualTo(900);
    }
}