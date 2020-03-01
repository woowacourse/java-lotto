package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    @DisplayName("로또 실행시 카운터가 맞게 올라가는지 확인")
    void resultLottoCount() {
        Ticket ticket = new Ticket("1, 2, 3, 4, 5, 6");
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers("1, 2, 3, 8, 9, 10", "7");
        LottoResult lottoResult = new LottoResult();
        lottoResult.resultCount(ticket, winLottoNumbers);
        assertThat(lottoResult.getResultCount(RankType.THREE)).isEqualTo(1);
    }
}
