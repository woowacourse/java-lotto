package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultCountTest {

    @Test
    @DisplayName("로또 실행시 카운터가 맞게 올라가는지 확인")
    void resultLottoCount() {
        Tickets tickets = new Tickets(Arrays.asList(new Ticket("1, 2, 3, 4, 5, 6")));
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers("1, 2, 3, 8, 9, 10", "7");
        LottoResultCount lottoResultCount = new LottoResultCount(tickets, winLottoNumbers);
        assertThat(lottoResultCount.getResultCount(RankType.THREE)).isEqualTo(1);
    }
}
