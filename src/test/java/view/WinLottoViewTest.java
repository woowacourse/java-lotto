package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinLottoViewTest {
    @ParameterizedTest
    @ValueSource(strings = {"a, b, c, d, e, f", "1, 2, 3, 4, 5, -1"})
    @DisplayName("양의 정수 판별 테스트")
    public void positiveIntegerTest(String input) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(winLottoView::readWinNumbers)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
    }
}
