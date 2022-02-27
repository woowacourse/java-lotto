package model.lotto;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.result.WinningResult;
import model.bonusball.BonusBallDTO;
import model.result.Rank;
import model.winningnumber.LottoWinningNumberDTO;

public class LottoTest {
    private WinningResult winningResult;

    @BeforeEach
    void init() {
        winningResult = new WinningResult();
    }

    @Test
    @DisplayName("당첨 번호와 로또의 비교값이 5인 경우")
    void compareWinningNumberWithLottoFive() {
        Lotto lotto = new Lotto(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));
        LottoWinningNumberDTO lottoWinningNumberDTO =
                new LottoWinningNumberDTO(new HashSet<>(List.of(1, 2, 3, 4, 5, 7)));
        BonusBallDTO bonusBallDTO = new BonusBallDTO(8);

        lotto.calcWinningNumber(winningResult, bonusBallDTO, lottoWinningNumberDTO);

        assertThat(winningResult.getWinningCount().get(Rank.BONUS)).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨 번호와 로또의 비교값이 5이고, 보너스가 존재하는 경우")
    void compareWinningNumberWithLotto() {
        Lotto lotto = new Lotto(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));
        LottoWinningNumberDTO lottoWinningNumberDTO =
                new LottoWinningNumberDTO(new HashSet<>(List.of(1, 2, 3, 4, 5, 7)));
        BonusBallDTO bonusBallDTO = new BonusBallDTO(6);

        lotto.calcWinningNumber(winningResult, bonusBallDTO, lottoWinningNumberDTO);

        assertThat(winningResult.getWinningCount().get(Rank.BONUS)).isEqualTo(1);
    }
}
