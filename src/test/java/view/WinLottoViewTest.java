package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinLottoViewTest {
    @Test
    public void 숫자개수_판별_테스트() {
        // given
        String input = "1, 2, 3, 4, 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(() -> winLottoView.readWinNumbers())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_COUNT_EXCEPTION);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a, b, c, d, e, f", "1, 2, 3, 4, 5, -1"})
    public void 양의정수_판별_테스트(String test) {
        // given
        System.setIn(new ByteArrayInputStream(test.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(() -> winLottoView.readWinNumbers())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
    }
}
