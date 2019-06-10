package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = Lotto.of(new Numbers(Arrays.asList(1,2,3,4,5,6)));
    }

    @Test
    void 생성() {
        Winning winning = Winning.of(lotto, 7);
        assertThat(winning).isEqualTo(Winning.of(lotto, 7));
    }

    @Test
    void 보너스번호_범위_확인() {
        assertThrows(InvalidWinningException.class, () -> Winning.of(lotto, 46));
        assertThrows(InvalidWinningException.class, () -> Winning.of(lotto, 0));
    }

    @Test
    void 보너스번호가_당첨번호에_포함되는지() {
        assertThrows(InvalidWinningException.class, () -> Winning.of(lotto, 5));
    }
}
