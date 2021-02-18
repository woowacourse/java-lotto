package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        List<Result> results = lottos.getResults(new WinningLotto(winningNumbers, bonusNumber));

        List<Result> expectedResults = Arrays.asList(Result.FIFTH, Result.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }

    @DisplayName("로또들을 만들어 주는 테스트")
    @Test
    void makeLottos() {
        Lottos lottos1 = new Lottos(3);

        assertThat(lottos1.getNumberOfLotto()).isEqualTo(3);
    }
}
