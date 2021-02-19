package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    @DisplayName("당첨번호 생성 확인")
    @Test
    void correctWinningNumber(){
        String input = "1, 2, 3, 4, 5, 6";

        WinningNumber winningNumber = new WinningNumber(input);

        assertThat(winningNumber).isEqualTo(new WinningNumber("1, 2, 3, 4, 5, 6"));
    }

    @DisplayName("숫자 인지 확인")
    @Test
    void isNum(){
        String input = "1, a, 3, 4, 5, 6";

        assertThatThrownBy(()->{
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 숫자여야만 합니다.");
    }

    @DisplayName("1~45사의 숫자인지 확인")
    @Test
    void is1to45(){
        String input = "1, 2, 3, 4, 5, 46";

        assertThatThrownBy(()->{
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("중복된 당첨번호 확인")
    @Test
    void duplicateCheck(){
        String input = "1, 1, 2, 3, 4, 5";

        assertThatThrownBy(()->{
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
