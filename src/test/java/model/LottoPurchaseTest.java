package model;

import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.message.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_FORMAT;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseTest {

    @Test
    @DisplayName("null이 들어왔을 때 예외 처리된다.")
    void inputWithNull() {
        assertThatThrownBy(() -> LottoPurchase.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("빈 문자열이 들어왔을 때 예외 처리된다.")
    void inputWithBlanl() {
        assertThatThrownBy(() -> LottoPurchase.of(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("문자가 들어왔을 때 예외 처리된다.")
    void inputWithCharacter() {
        assertThatThrownBy(() -> LottoPurchase.of("ㅁ"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_PURCHASE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("1000원 이하면 예외 처리된다.")
    void inputWithMinPurchase() {
        assertThatThrownBy(() -> LottoPurchase.of("999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_MIN_PURCHASE.getMessage(1000));
    }

    @Test
    @DisplayName("1000원 단위가 아니면 예외 처리된다.")
    void inputWithPurchaseUnit() {
        assertThatThrownBy(() -> LottoPurchase.of("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_PURCHASE_UNIT.getMessage(1000));
    }
}
