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
    @DisplayName("당첨 번호에 따른 로또 매칭 결과가 제대로 계산되는지")
    void matchCount() {
        LottoResult lottoResult = lottoTickets.match(LottoTicketTest.WINNING_TICKET);
        assertThat(lottoResult.countSpecificRank(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("입력받은 돈과 수동로또의 개수에 따라 올바르게 자동 티켓을 생성하는지")
    void addManualAndAutoLottoTickets() {
        List<LottoTicket> manualTickets = new ArrayList<>();
        manualTickets.add(new LottoTicket(LottoTicketTest.MATCH_NONE));
        manualTickets.add(new LottoTicket(LottoTicketTest.MATCH_FIVE));
        manualTickets.add(new LottoTicket(LottoTicketTest.MATCH_THREE));
        assertThat(LottoTickets.createAutoAndAdd(new Money("5000"), manualTickets).getLottoTickets().size()).isEqualTo(
            5);
    }
}