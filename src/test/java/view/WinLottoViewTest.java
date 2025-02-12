package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

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
}
