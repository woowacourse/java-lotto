package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("원하는 만큼 수동 + 자동으로 로또티켓을 구입")
    void buyLottos() {
        List<List<Integer>> manualLottos = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        Lottos lottos = new Lottos(manualLottos, 7);
        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }
}
