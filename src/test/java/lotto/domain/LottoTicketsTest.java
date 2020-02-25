package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private static LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        String[][] numbers = {
            LottoTicketTest.MATCH_THREE,
            LottoTicketTest.MATCH_TWO,
            LottoTicketTest.MATCH_ONE,
            LottoTicketTest.MATCH_ONE,
            LottoTicketTest.MATCH_NONE
        };
        List<LottoTicket> sampleTickets = new ArrayList<>();
        for (String[] number : numbers) {
            sampleTickets.add(new LottoTicket(number));
        }
        lottoTickets = new LottoTickets(sampleTickets);
    }

    @Test
    @DisplayName("입력받은 돈에 따른 올바른 티켓 갯수를 생성하는지")
    void ticketQuantity() {
        LottoTickets lottoTickets = LottoTickets.createLottoTickets(new Money("5000"));
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("당첨 번호와 매칭되는 로또티켓 갯수가 제대로 계산되는지")
    void matchCount() {
        LottoResult lottoResult = lottoTickets.match(LottoTicketTest.WINNING_TICKET);
        assertThat(lottoResult.countSpecificRank(Rank.FOURTH)).isEqualTo(1);
    }

}