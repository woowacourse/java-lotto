package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("당첨 번호와 중복되는 보너스 볼 예외 테스트")
    public void createDuplicatedNumberBetweenWinningAndBonus() {
        int bonusNumber = 1;
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1");
    }

    @Test
    @DisplayName("유효한 당첨 번호와 보너스 볼로 클래스가 생성되는지 테스트")
    public void createWinningNumberWithValidNumbers() {
        assertDoesNotThrow(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @Test
    @DisplayName("1등 당첨 테스트")
    public void countFirstLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_1);
    }

    @Test
    @DisplayName("2등 당첨 테스트")
    public void countSecondLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_2);
    }

    @Test
    @DisplayName("3등 당첨 테스트")
    public void countThirdLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_3);
    }

    @Test
    @DisplayName("4등 당첨 테스트")
    public void countFourthLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 7, 8));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_4);
    }

    @Test
    @DisplayName("5등 당첨 테스트")
    public void countFifthLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_5);
    }

    @Test
    @DisplayName("꽝 테스트")
    public void countNoLottoRank() {
        Lotto lotto = new Lotto(Arrays.asList(5, 6, 7, 8, 9, 10));
        LottoRank rank = winningLotto.countLottoRank(lotto);
        assertThat(rank).isEqualTo(LottoRank.RANK_NOTHING);
    }
}
