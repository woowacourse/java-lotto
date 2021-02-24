package lotto.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PurchaseCountTest {
    
    @Test
    @DisplayName("구매 개수 생성 테스트")
    void init() {
        
        // given
        PaymentAmount paymentAmount = PaymentAmount.from("14000");
        String manualCount = "3";
        PurchaseCount purchaseCount = PurchaseCount.of(paymentAmount, manualCount);
        
        // when
        int manualPurchaseCount = purchaseCount.getManualPurchaseCount();
        int automaticPurchaseCount = purchaseCount.getAutomaticPurchaseCount();
        
        // then
        assertThat(manualPurchaseCount).isEqualTo(3);
        assertThat(automaticPurchaseCount).isEqualTo(11);
    }
    
    @Test
    @DisplayName("입력 값이 숫자가 아닐 경우 예외 발생")
    void init_isNotInteger_ExceptionThrown() {
        
        // given
        PaymentAmount paymentAmount = PaymentAmount.from("14000");
        String manualCount = "문자";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PurchaseCount.of(paymentAmount, manualCount);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("입력 값이 음수일 경우 예외 발생")
    void init_isNegative_ExceptionThrown() {
        
        // given
        PaymentAmount paymentAmount = PaymentAmount.from("14000");
        String manualCount = "-5";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PurchaseCount.of(paymentAmount, manualCount);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("수동 구매 개수가 전체 로또 구매 개수보다 클 경우 예외 발생")
    void init_isBiggerThanTotal_ExceptionThrown() {
        
        // given
        PaymentAmount paymentAmount = PaymentAmount.from("14000");
        String manualCount = "15";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PurchaseCount.of(paymentAmount, manualCount);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
}