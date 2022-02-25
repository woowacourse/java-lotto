package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    @DisplayName("일치하는 번호 개수에 따른 순위 확인 테스트")
    public void checkMatchNumber() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto allMatchLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Rank rank = allMatchLotto.match(winningNumber);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("보너스 볼과 일치할 때 2등 당첨 테스트")
    public void checkBonusBallMatchTest() {
        Lotto fiveMatchLotto = new Lotto(List.of(1, 2, 3, 4, 5, 44));
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 44);
        Rank rank = fiveMatchLotto.match(winningNumber);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
