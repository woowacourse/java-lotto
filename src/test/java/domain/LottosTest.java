package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    void of_createTheSameNumberOfLottosAsLottoCount() {
        Lottos lottos = Lottos.of(5);

        assertThat(lottos.getLottos().size()).isEqualTo(5);
    }

    @Test
    void of_eachLottoIsDifferent() {
        Lottos lottos = Lottos.of(20);

        Set<Lotto> noDuplicateLottoSet = new HashSet<>(lottos.getLottos());
        assertThat(noDuplicateLottoSet.size())
                .isEqualTo(lottos.getLottos().size());
    }
}
