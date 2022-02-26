package domain;

import static common.TestUtils.createNewLotto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private static final int MANUAL_LOTTOS_COUNT = 3;
    private static final List<Lotto> manualLottos = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        manualLottos.add(createNewLotto(1, 2, 3, 4, 5, 6));
        manualLottos.add(createNewLotto(1, 2, 3, 4, 5, 16));
        manualLottos.add(createNewLotto(1, 2, 3, 4, 5, 26));
    }

    @Test
    void of_createsTheSameNumberOfLottosAsManualLottosSizeAndRandomCount() {
        Lottos lottos = Lottos.of(manualLottos, 5);

        assertThat(lottos.getLottos().size()).isEqualTo(MANUAL_LOTTOS_COUNT + 5);
    }

    @Test
    void of_allNewLottosAreDifferent() {
        Lottos lottos = Lottos.of(manualLottos, 5);

        Set<Lotto> noDuplicateLottoSet = new HashSet<>(lottos.getLottos());
        assertThat(noDuplicateLottoSet.size())
                .isEqualTo(lottos.getLottos().size());
    }

    @Test
    void of_passesOnRandomOnly() {
        Lottos lottos = Lottos.of(new ArrayList<>(), 5);
        assertThat(lottos.getLottos().size()).isEqualTo(5);
    }

    @Test
    void of_passesOnManualOnly() {
        Lottos lottos = Lottos.of(manualLottos, 0);
        assertThat(lottos.getLottos().size()).isEqualTo(MANUAL_LOTTOS_COUNT);
    }

    @Test
    void ofRandom_createsTheSameNumberOfLottosAsRandomCount() {
        Lottos lottos = Lottos.ofRandom(5);

        assertThat(lottos.getLottos().size()).isEqualTo(5);
    }

    @Test
    void getLottos_throwsExceptionOnDirectModification() {
        Lottos lottos = Lottos.of(manualLottos, 5);

        Lotto newLotto = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> lottos.getLottos().add(newLotto));
    }
}
