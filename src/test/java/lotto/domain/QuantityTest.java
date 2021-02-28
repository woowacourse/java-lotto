package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class QuantityTest {

    @Test
    @DisplayName("주생성자, 부생성자 테스트")
    void testConstructor() {
        Quantity quantity = new Quantity("1");
        assertThat(quantity).isEqualTo(new Quantity(1));
    }

    @Test
    @DisplayName("예외 테스트")
    void testValidateEmpty() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Quantity("")
        ).withMessage("빈 값은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 입력 예외 테스트")
    void testValidateNumber() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Quantity("aa")
        ).withMessage("숫자만 입력 가능합니다.");
    }

    @Test
    @DisplayName("음수 예외 테스트")
    void testValidateNegative() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Quantity("-1")
        ).withMessage("로또 구매 수는 양수여야 합니다.");
    }

    @Test
    @DisplayName("개수만큼 로또 가격을 계산해서 반환 테스트")
    void testCalculatePrice() {
        Quantity quantity = new Quantity(1);
        Money actualMoney = new Money(1000);
        assertThat(quantity.calculateQuantityPrice()).isEqualTo(actualMoney);
    }
}
