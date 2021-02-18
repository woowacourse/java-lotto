package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lottogame.domain.ticket.LottoTicketResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("상금줘봐")
    void getPrice() {
        assertThat(Rank.getRank(new LottoTicketResult(6, false))).isEqualTo(Rank.RANK1);
        assertThat(Rank.getRank(new LottoTicketResult(5, true))).isEqualTo(Rank.RANK2);
        assertThat(Rank.getRank(new LottoTicketResult(5, false))).isEqualTo(Rank.RANK3);
        assertThat(Rank.getRank(new LottoTicketResult(4, false))).isEqualTo(Rank.RANK4);
        assertThat(Rank.getRank(new LottoTicketResult(3, false))).isEqualTo(Rank.RANK5);
        assertThat(Rank.getRank(new LottoTicketResult(2, false))).isEqualTo(Rank.FAIL);
    }
}
