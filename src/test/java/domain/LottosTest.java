package domain;

import static common.TestUtils.createNewLotto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private final static int LOTTO_COUNT = 20;
    private static Lottos lottos;

    @BeforeEach
    void setUp() {
        List<Lotto> randomLottos = Stream.generate(Lotto::new)
                .limit(LOTTO_COUNT)
                .collect(Collectors.toList());

        lottos = new Lottos(randomLottos);
    }

    @Test
    void createAndAddLottos_createTheSameNumberOfLottosAsLottoCount() {
        lottos.createAndAddLottos(5);

        assertThat(lottos.getLottos().size()).isEqualTo(LOTTO_COUNT + 5);
    }


    @Test
    void createAndAddLottos_allNewLottosAreDifferent() {
        lottos.createAndAddLottos(5);

        Set<Lotto> noDuplicateLottoSet = new HashSet<>(lottos.getLottos());
        assertThat(noDuplicateLottoSet.size())
                .isEqualTo(lottos.getLottos().size());
    }

    @Test
    void getLottos_throwsExceptionOnDirectModification() {
        Lotto newLotto = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> lottos.getLottos().add(newLotto));
    }
}
