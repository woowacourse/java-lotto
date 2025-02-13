package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinLottoViewTest {
    @Test
    @DisplayName("숫자 개수 판별 테스트")
    public void numberCountTest() {
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
    @DisplayName("양의 정수 판별 테스트")
    public void positiveIntegerTest(String input) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(() -> winLottoView.readWinNumbers())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
    }

    @Test
    @DisplayName("숫자 범위 판별 테스트")
    public void numberBoundTest() {
        // given
        String input = "46, 47, 48, 49, 50, 51";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(() -> winLottoView.readWinNumbers())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_BOUND_EXCEPTION);
    }

    @Test
    @DisplayName("숫자 중복 판별 테스트")
    public void numberDuplicateTest() {
        // given
        String input = "1, 2, 3, 4, 5, 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        WinLottoView winLottoView = new WinLottoView();

        // when & then
        assertThatThrownBy(() -> winLottoView.readWinNumbers())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_DUPLICATE_EXCEPTION);
    }
}
