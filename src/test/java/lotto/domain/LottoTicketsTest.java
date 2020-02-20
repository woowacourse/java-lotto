package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        String[][] numbers = {
            LottoTicketTest.MATCH_BONUS,
            LottoTicketTest.MATCH_FIVE,
        };
        List<LottoTicket> sampleTickets = new ArrayList<>();
        for (String[] number : numbers) {
            sampleTickets.add(new LottoTicket(number));
        }
        lottoTickets = new LottoTickets(sampleTickets);
    }

    @Test
    @DisplayName("당첨 통계가 제대로 나오는지")
    void result() {
        LottoTicket winningTicket = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        Map<Rank, Integer> result = lottoTickets.matchResult(winningTicket, LottoNumber.SEVEN);
        assertThat(result.get(Rank.BONUS)).isEqualTo(1);
        assertThat(result.getOrDefault(Rank.SECOND, 0)).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        Rank.reset();
    }
}