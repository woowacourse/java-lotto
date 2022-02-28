import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import domain.Rewards;
import domain.WinningNumbers;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또 장수 확인")
    void check_lottos_size() {

        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(4, 5, 6, 7, 8, 9));
        Lotto lotto3 = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        assertThat(lottos.getSize()).isEqualTo(3);
    }


}
