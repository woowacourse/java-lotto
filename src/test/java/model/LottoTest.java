package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoTest {

    @Test
    @DisplayName("당첨 번호와 로또 비교")
    void compareWinningNumberWithLotto() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        LottoWinningNumberDTO lottoWinningNumberDTO = new LottoWinningNumberDTO(Arrays.asList(1,2,3,4,5,7));
        lotto.compare(lottoWinningNumberDTO);
        assertThat(Statistics.FIVE.getCount()).isEqualTo(1);
    }
}
