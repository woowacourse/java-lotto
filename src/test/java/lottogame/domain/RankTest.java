package lottogame.domain;

import lottogame.domain.ticket.LottoTicketResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    @DisplayName("당첨번호의 개수가 일치하고, 보너스 번호가 틀릴때 원하는 RANK를 가져온다.")
    void matchCountTest() {
        assertThat(Rank.getRank(new LottoTicketResult(6, false))).isEqualTo(Rank.RANK1);
        assertThat(Rank.getRank(new LottoTicketResult(5, false))).isEqualTo(Rank.RANK3);
        assertThat(Rank.getRank(new LottoTicketResult(4, false))).isEqualTo(Rank.RANK4);
        assertThat(Rank.getRank(new LottoTicketResult(3, false))).isEqualTo(Rank.RANK5);
        assertThat(Rank.getRank(new LottoTicketResult(2, false))).isEqualTo(Rank.FAIL);
    }

    @Test
    @DisplayName("4등 이하에서 당첨번호 당첨번호의 개수가 일치하고, 보너스 번호가 맞을때 원하는 RANK를 가져온다.")
    void matchCountAndBonusMatchTest() {
        assertThat(Rank.getRank(new LottoTicketResult(3, true))).isEqualTo(Rank.RANK5);
    }

    @Test
    @DisplayName("2등과 3등의 경우를 구별해서 원하는 RANK를 가져온다.")
    void matchSecondThird(){
        assertThat(Rank.getRank(new LottoTicketResult(5, true))).isEqualTo(Rank.RANK2);
        assertThat(Rank.getRank(new LottoTicketResult(5, false))).isEqualTo(Rank.RANK3);
    }
}
