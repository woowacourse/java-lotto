package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinNumberTest {
    @Test
    @DisplayName("당첨번호가 6개를 넘을때")
    void isOverSix() {
        assertThatThrownBy(() -> {
            List<Integer> 당첨번호1 = Arrays.asList(1, 3, 5, 7, 9, 11, 13);
            new WinNumber(당첨번호1);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호가 6개보다 부족할 때")
    void isUnderSix() {
        assertThatThrownBy(() -> {
            List<Integer> 당첨번호2 = Arrays.asList(1, 3, 5, 7, 9);
            new WinNumber(당첨번호2);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }
}
