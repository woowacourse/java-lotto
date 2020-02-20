package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {
    @Test
    @DisplayName("당첨 번호가 null 문자열")
    void isNullOrEmptyString() {
        String input1 = null;
        String input2 = "";
        assertThatThrownBy(() -> {
            InputView.getWinNumbers(input1);
        }).isInstanceOf(NullPointerException.class)
                .hasMessage("당첨 번호에 null 혹은 빈문자열을 입력할 수 없습니다.");
        assertThatThrownBy(() -> {
            InputView.getWinNumbers(input2);
        }).isInstanceOf(NullPointerException.class)
                .hasMessage("당첨 번호에 null 혹은 빈문자열을 입력할 수 없습니다.");

    }
}
