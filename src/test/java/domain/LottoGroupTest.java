package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGroupTest {
    private final List<Lotto> nonRandomLottos = Arrays.asList(
            LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
            LottoGenerator.from(Arrays.asList(2, 3, 4, 5, 6, 7))
    );
    private final List<Lotto> randomLottos = Arrays.asList(LottoGenerator.from(Arrays.asList(40, 41, 42, 43, 44, 45)));
    private final LottoGroup lottoGroup = LottoGroup.of(nonRandomLottos, randomLottos);

    @Test
    void contains_nonRandomLottos에_있는값() {
        assertThat(lottoGroup.contains(nonRandomLottos.get(0))).isTrue();
    }

    @Test
    void contains_randomLottos에_있는값() {
        assertThat(lottoGroup.contains(randomLottos.get(0))).isTrue();
    }

    @Test
    void totalSize_() {
        assertThat(lottoGroup.totalSize()).isEqualTo(nonRandomLottos.size() + randomLottos.size());
    }

    @Test
    void nonRandomSize_() {
        assertThat(lottoGroup.nonRandomSize()).isEqualTo(nonRandomLottos.size());
    }

    @Test
    void randomSize_() {
        assertThat(lottoGroup.randomSize()).isEqualTo(randomLottos.size());
    }
}