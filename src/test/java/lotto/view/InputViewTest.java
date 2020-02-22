package lotto.view;

import lotto.exception.NullOrEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {
    @Test
    @DisplayName("당첨 번호가 null 문자열")
    void isNullString() {
        String winNumber1 = null;

        assertThatThrownBy(() -> {
            InputView.getWinNumbers(winNumber1);
        }).isInstanceOf(NullOrEmptyException.class)
                .hasMessage("null 혹은 빈문자열을 입력할 수 없습니다.");
    }

    @Test
    void isEmptyString() {
        String winNumber2 = "";

        assertThatThrownBy(() -> {
            InputView.getWinNumbers(winNumber2);
        }).isInstanceOf(NullOrEmptyException.class)
                .hasMessage("null 혹은 빈문자열을 입력할 수 없습니다.");
    }
}
