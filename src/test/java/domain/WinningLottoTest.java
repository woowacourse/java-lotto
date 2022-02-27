package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private List<Integer> winningNumbers;
    private int bonusNumber;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        bonusNumber = 7;
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @Test
    public void createDuplicatedNumberBetweenWinningAndBonus() {
        int bonusNumber = 1;
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1");
    }

    @Test
    public void createWinningNumberWithValidNumbers() {
        assertDoesNotThrow(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @Test
    public void countFirstLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_1);
    }

    @Test
    public void countSecondLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_2);
    }

    @Test
    public void countThirdLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_3);
    }

    @Test
    public void countFourthLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 7, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_4);
    }

    @Test
    public void countFifthLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_5);
    }

    @Test
    public void countNoLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(5, 6, 7, 8, 9, 10));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_NOTHING);
    }
}
