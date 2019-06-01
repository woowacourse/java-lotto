package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    // 보너스 체크
    private final WinningLotto winningLotto = WinningLotto.of(createLotto(Arrays.asList(1, 2, 3, 4, 5, 6)), Number.from(45));

    @Test
    void match_MISS() {
        Lotto userLotto = createLotto(Arrays.asList(11, 12, 13, 14, 15, 16));

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.MISS);
    }

    @Test
    void match_FIRST() {
        Lotto userLotto = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIRST);
    }

    //    SECOND(5, 30_000_000), // 2등

    private Lotto createLotto(List<Integer> integers) {
        List<Number> numbers = integers.stream().map(i -> Number.from(i)).collect(Collectors.toList());
        return new Lotto(numbers);
    }
}