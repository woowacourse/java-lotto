package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("당첨번호와 비교하여 알맞은 당첨 등수 반환")
    void getRank() {
        WinningNumbers winningNumbersInstance = new WinningNumbers("1,2,3,4,5,45", "6");
        Lotto lotto = Lotto.generateLottoByManual("1,2,3,4,5,6");
        Assertions.assertThat(lotto.getRank(winningNumbersInstance)).isEqualTo(Rank.RANK_2);
    }
}
