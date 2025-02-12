package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseViewTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "-1000", "0"})
    public void 양의정수_판별_테스트(String test) {
        // given
        System.setIn(new ByteArrayInputStream(test.getBytes()));
        PurchaseView purchaseView = new PurchaseView();

        // when & then
        assertThatThrownBy(() -> purchaseView.readPurchaseAmount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
    }

    @Test
    public void _1000의_배수_판별_테스트() {
        //given
        String test = "1501";
        System.setIn(new ByteArrayInputStream(test.getBytes()));
        PurchaseView purchaseView = new PurchaseView();

        // when & then
        assertThatThrownBy(() -> purchaseView.readPurchaseAmount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DIVIDABLE_EXCEPTION);
    }
}
