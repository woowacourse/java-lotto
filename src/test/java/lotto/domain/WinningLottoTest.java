package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto
            (Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
    }

    @Test
    public void match_1등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    public void match_2등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    public void match_3등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 8));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    public void match_4등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 7, 8));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    public void match_5등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 7, 8, 9));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    public void match_꽝() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 7, 8, 9, 10));
        assertThat(winningLotto.match(userLotto)).isEqualTo(LottoRank.NO_MATCH);
    }

    @DisplayName("6개의 당첨 번호는 서로 다른 번호여야한다.")
    @Test
    void 중복된_당첨_번호_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("당첨 번호는 6개로 구성되어있어야한다.")
    @Test
    void 당첨_번호_길이_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("보너스 숫자는 1부터 45사이의 번호여야한다.")
    @Test
    void 보너스_숫자_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = -1;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("보너스 번호는 당첨 번호는 없는 번호여야한다.")
    @Test
    void 보너스_번호와_당첨_번호_다른지_확인_테스트() {
        // given, when
        Lotto winningLottoValue = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int winningBonus = 6;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new WinningLotto(winningLottoValue, winningBonus);
        });
    }
}