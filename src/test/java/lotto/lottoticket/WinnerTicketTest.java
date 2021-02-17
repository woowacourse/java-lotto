package lotto.lottoticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinnerTicketTest {
    @Test
    @DisplayName("당첨 번호 생성 확인")
    void winnerTicketCreate() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        assertThat(winnerTicket).isEqualTo(new WinnerTicket("1, 2, 3, 4, 5, 6"));
    }

    @ParameterizedTest
    @DisplayName("잘못된 구분자를 사용한 경우")
    @ValueSource(strings = {"1, 2, 3, 4, 5. 6", "1#2,3,4,5,6", "1,,2,3,4,5,6"})
    void checkWrongDelimiter(String value) {
        assertThatThrownBy(() -> new WinnerTicket(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 경우")
    void checkNotNumber() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @Test
    @DisplayName("숫자의 범위가 1부터 45사이의 수가 아닌 경우")
    void checkNumberInRange() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1부터 45사이여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void checkNumbersSize() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 여섯개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 중복되는 경우")
    void checkDuplicated() {
        assertThatThrownBy(() -> new WinnerTicket("1,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 입력입니다.");
    }
}
