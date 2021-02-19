package lotto.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PaymentAmountTest {
    
    @Test
    @DisplayName("구입 개수 테스트")
    void getPurchaseCountTest() {
        
        // given
        String paymentAmount = "14000";
        
        // when
        int payment = PaymentAmount.from(paymentAmount).getPurchaseCount();
        
        // then
        assertThat(payment).isEqualTo(14);
    }
    
    @Test
    @DisplayName("숫자가 아닐 경우 예외 발생")
    void isInteger_NotInteger_ExceptionThrown() {
        
        // given
        String input = "a";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PaymentAmount.from(input);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("음수일 경우 예외 발생")
    void isZeroOrPositive_Negative_ExceptionThrown() {
        
        // given
        String input = "-1";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PaymentAmount.from(input);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("로또 가격의 배수가 아닐 경우 예외 발생")
    void isMultipleOfLottoPayment_NotMultiple_ExceptionThrown() {
        
        // given
        String input = "1100";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> PaymentAmount.from(input);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
}