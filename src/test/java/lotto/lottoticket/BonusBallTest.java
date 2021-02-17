package lotto.lottoticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BonusBallTest {
    @Test
    @DisplayName("보너스 볼 생성")
    void bonusBallCreate(){
        BonusBall bonusBall = new BonusBall("10", new WinnerTicket("1, 2, 3, 4, 5, 6"));
        assertThat(bonusBall).isEqualTo(new BonusBall("10", new WinnerTicket("1, 2, 3, 4, 5, 6")));
    }

    @Test
    @DisplayName("숫자가 아닌 경우")
    void bonusBallNotNumber() {
        assertThatThrownBy(() ->
                new BonusBall("*", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @Test
    @DisplayName("숫자의 범위가 1부터 45사이의 수가 아닌 경우")
    void checkNumberInRange() {
        assertThatThrownBy(() -> new BonusBall("46", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1부터 45사이여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 중복되는 경우")
    void checkDuplicated() {
        assertThatThrownBy(() -> new BonusBall("6", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 숫자가 존재합니다.");
    }
}
