package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.LottoRule.LottoNumber;

class LottoResultTest {
    private LottoTickets lottoTickets;

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
    @DisplayName("당첨 번호와 매칭되는 로또티켓 갯수가 제대로 계산되는지")
    void matchCount() {
        LottoResult lottoResult = new LottoResult();
        LottoTicket winningNumber = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        WinningTicket winningTicket = new WinningTicket(winningNumber, new LottoNumber(7));
        lottoResult.matchResult(winningTicket, lottoTickets);
        assertThat(lottoResult.getResult().get(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률이 정상적으로 계산되는지")
    void calculateRate() {
        LottoResult lottoResult = new LottoResult();
        LottoTicket winningNumber = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        WinningTicket winningTicket = new WinningTicket(winningNumber, new LottoNumber(7));
        lottoResult.matchResult(winningTicket, lottoTickets);
        assertThat(lottoResult.calculateRate(Money.create("5000"))).isEqualTo("100.00");
    }
}
