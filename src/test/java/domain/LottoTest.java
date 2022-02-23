package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    @Test
    @DisplayName("일치하는 번호 개수 확인 테스트")
    public void checkMatchNumber() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        Lotto allMatchLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int count = allMatchLotto.match(winningNumber);

        assertThat(count).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스 볼과 일치하는 번호가 있는지 테스트")
    public void checkBonusBallMatchTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusBall = 4;

        assertTrue(lotto.hasBonusBall(bonusBall));
    }
}
