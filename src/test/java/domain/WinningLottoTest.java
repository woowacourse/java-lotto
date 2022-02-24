package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    public void createDuplicatedNumberBetweenWinningAndBonus() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createWinningNumberWithValidNumbers() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        assertDoesNotThrow(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @Test
    public void countFirstLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_1);
    }

    @Test
    public void countSecondLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_2);
    }

    @Test
    public void countThirdLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_3);
    }

    @Test
    public void countFourthLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 7, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_4);
    }

    @Test
    public void countFifthLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_5);
    }

    @Test
    public void countNoLottoRank() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(Arrays.asList(5, 6, 7, 8, 9, 10));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_NOTHING);
    }
}
