package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 번호가 당첨번호와 일치하는지 테스트")
    public void success_1() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // 로또 번호가 5개 일치하고, 보너스 번호는 포함되지 않은 경우
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 10));
        LottoRank rank = lotto.checkLottoRank(winningLotto);
        Assertions.assertThat(rank).isEqualTo(LottoRank.THIRD_PLACE);
    }

    @Test
    @DisplayName("당첨 번호가 하나도 없는 경우 테스트")
    public void success_2() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // 하나도 맞지 않는 경우
        Lotto lotto = new Lotto(List.of(11, 12, 13, 14, 15, 16));
        LottoRank rank = lotto.checkLottoRank(winningLotto);
        Assertions.assertThat(rank).isEqualTo(LottoRank.BOOM);
    }
}
