package lotto.domain;

import lotto.exception.BonusBallValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class WinningLottoTest {
    Lotto winningNumbers;
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningLotto = new WinningLotto(winningNumbers, LottoNumber.of(7));
    }

    @Test
    void 생성확인() {
        assertThat(WinningLotto.generateWinningLotto("1,2,3,4,5,6", 7)).isEqualTo(winningLotto);
    }

    @Test
    void 보너스볼_중복_예외() {
        assertThrows(BonusBallValidException.class, () -> {
            new WinningLotto(winningNumbers, LottoNumber.of(6));
        });
    }

    @Test
    void 일등_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)))).isEqualTo(FIRST);
    }

    @Test
    void 이등_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)))).isEqualTo(SECOND);
    }

    @Test
    void 삼등_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10)))).isEqualTo(THIRD);
    }

    @Test
    void 사등_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 10, 11)))).isEqualTo(FOURTH);
    }

    @Test
    void 오등_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12)))).isEqualTo(FIFTH);
    }

    @Test
    void 꽝_확인() {
        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(1, 2, 10, 11, 12, 13)))).isEqualTo(MISS);
    }

}
