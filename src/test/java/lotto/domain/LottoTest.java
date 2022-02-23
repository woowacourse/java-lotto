package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void getRank() {
        WinningNumbers winningNumbersInstance = new WinningNumbers("1,2,3,4,5,45", "6");
        Lotto lotto = Lotto.generateLottoByManual("1,2,3,4,5,6");
        Assertions.assertThat(lotto.getRank(winningNumbersInstance)).isEqualTo(Rank.RANK_2);
    }
}
