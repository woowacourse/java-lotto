package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 20, 25, 29, 45));
        lottos = new Lottos(Arrays.asList(lotto1, lotto2));
    }

    @Test
    void testEntireLottoMatching() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 20;
        List<Results> results = lottos.getResults(winningNumbers, bonusNumber);

        List<Results> expectedResults = Arrays.asList(Results.FIFTH, Results.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }
}
