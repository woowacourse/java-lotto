package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        List<LottoTicket> sampleTickets = new ArrayList<>();
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"})); // 6
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "4", "5", "7"})); // 5
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "4", "5", "8"})); // 5
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "4", "7", "8"})); // 4
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "7", "8", "9"})); // 3
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "10", "7", "8", "9"})); // 2
        sampleTickets.add(new LottoTicket(new String[] {"1", "11", "10", "7", "8", "9"})); // 1
        sampleTickets.add(new LottoTicket(new String[] {"2", "11", "10", "7", "8", "9"})); // 1
        sampleTickets.add(new LottoTicket(new String[] {"12", "11", "10", "7", "8", "9"})); // 0
        lottoTickets = new LottoTickets(sampleTickets);
    }

    @Test
    void result() {
        LottoTicket winningTicket = new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"});
        Map<Integer, Integer> result = lottoTickets.matchResult(winningTicket, LottoNumber.SEVEN);
        assertThat(result.get(1)).isEqualTo(2);
        assertThat(result.get(-1)).isEqualTo(1);
        assertThat(result.getOrDefault(5, 0)).isEqualTo(1);
    }
}