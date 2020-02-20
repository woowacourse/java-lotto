package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountantTest {
    private LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        List<LottoTicket> sampleTickets = new ArrayList<>();
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "3", "7", "8", "9"})); // 3
        sampleTickets.add(new LottoTicket(new String[] {"1", "2", "10", "7", "8", "9"})); // 2
        sampleTickets.add(new LottoTicket(new String[] {"1", "11", "10", "7", "8", "9"})); // 1
        sampleTickets.add(new LottoTicket(new String[] {"2", "11", "10", "7", "8", "9"})); // 1
        sampleTickets.add(new LottoTicket(new String[] {"12", "11", "10", "7", "8", "9"})); // 0
        lottoTickets = new LottoTickets(sampleTickets);
    }

    @Test
    void calculate() {
        LottoTicket winningTicket = new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"});
        lottoTickets.matchResult(winningTicket, LottoNumber.SEVEN);
        String test = Accountant.calculate(new Money("5000"));
        assertThat(test).isEqualTo("100.00");
    }
}