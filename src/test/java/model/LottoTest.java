package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoTest {

    @Test
    @DisplayName("당첨 번호와 로또의 비교값이 5인 경우")
    void compareWinningNumberWithLottoFive() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoWinningNumberDTO lottoWinningNumberDTO = new LottoWinningNumberDTO(Arrays.asList(1, 2, 3, 4, 5, 7));
        BonusBallDTO bonusBallDTO = new BonusBallDTO(8);
        lotto.compare(bonusBallDTO, lottoWinningNumberDTO);

        assertThat(Statistics.BONUS.getCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨 번호와 로또의 비교값이 5이고, 보너스가 존재하는 경우")
    void compareWinningNumberWithLotto() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoWinningNumberDTO lottoWinningNumberDTO = new LottoWinningNumberDTO(Arrays.asList(1, 2, 3, 4, 5, 7));
        BonusBallDTO bonusBallDTO = new BonusBallDTO(6);
        lotto.compare(bonusBallDTO, lottoWinningNumberDTO);

        assertThat(Statistics.BONUS.getCount()).isEqualTo(1);
    }
}
