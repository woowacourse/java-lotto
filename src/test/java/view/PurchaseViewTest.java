package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseViewTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "-1000", "0"})
    @DisplayName("양의정수 판별 테스트")
    public void positiveIntegerTest(String test) {
        // given
        System.setIn(new ByteArrayInputStream(test.getBytes()));
        PurchaseView purchaseView = new PurchaseView();

        // when & then
        assertThatThrownBy(() -> purchaseView.readPurchaseAmount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
    }
}
