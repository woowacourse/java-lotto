package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc", "-1000", "0"})
    @DisplayName("양의정수 판별 테스트")
    public void validatePositiveNumberTest(String test) {
        // given
        System.setIn(new ByteArrayInputStream(test.getBytes()));
        InputView inputView = new InputView();

        // when & then
        assertThatThrownBy(inputView::readPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("양의 정수를 입력해주세요.");
    }
}
