import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import domain.Rewards;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("또로첨 당등 1 갯수 확인하는 기능")
    void compareAllLottoTest1() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 30;

        lottos.compareAllLotto(winningNumbers, bonusNumber);

        assertThat(Rewards.getCount(Rewards.FIRST_REWARD)).isEqualTo(1);
    }
}
