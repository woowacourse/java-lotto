package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WinningLottoTicketTest {

    @DisplayName("당첨 번호와 보너스 볼 번호 문자열을 입력받아 객체를 생성한다")
    @Test
    void make() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusBallNumber = 7;

        assertThatCode(() -> {
            WinningLottoTicket.of(winningNumbers, bonusBallNumber);
        }).doesNotThrowAnyException();
    }

    @DisplayName("보너스 볼 번호는 당첨 번호와 중복되면 안 된다.")
    @Test
    void cannotMake() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusBallNumber = 6;

        assertThatCode(() -> {
            WinningLottoTicket.of(winningNumbers, bonusBallNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
