package domain;

import exception.BonusBallDuplicationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    // 보너스 체크
    private final Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));

    @Test
    void 생성자_로또와_보너스볼이_겹칠때() {
        assertThrows(BonusBallDuplicationException.class, () -> WinningLotto.of(lotto, Number.from(1)));
    }

    @Test
    void match_MISS() {
        WinningLotto winningLotto = WinningLotto.of(lotto, Number.from(45));
        Lotto userLotto = LottoGenerator.from(Arrays.asList(11, 12, 13, 14, 15, 16));

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.MISS);
    }

    @Test
    void match_FIRST() {
        WinningLotto winningLotto = WinningLotto.of(lotto, Number.from(45));
        Lotto userLotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void match_SECOND() {
        WinningLotto winningLotto = WinningLotto.of(lotto, Number.from(7));
        Lotto userLotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 7));

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.SECOND);
    }
}