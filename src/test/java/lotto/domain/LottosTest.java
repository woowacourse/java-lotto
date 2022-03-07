package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("원하는 만큼 수동 + 자동으로 로또티켓을 구입")
    void buyLottos() {
        List<List<Integer>> manualLottosInput = IntStream.range(0, 3)
                .mapToObj(i -> Arrays.asList(1, 2, 3, 4, 5, 6))
                .collect(Collectors.toList());
        Lottos manualLottos = Lottos.newInstanceByManual(manualLottosInput);
        Lottos autoLottos = Lottos.newInstanceByAuto(7);
        Lottos lottos = manualLottos.getCombinedLottos(autoLottos);
        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }
}
