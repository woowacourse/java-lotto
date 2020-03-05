package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTicketsTest {
    private LottoNumber eleven = LottoNumber.valueOf(11);
    private LottoNumber thirteen = LottoNumber.valueOf(11);
    private LottoNumber fifteen = LottoNumber.valueOf(11);
    private LottoNumber seventeen = LottoNumber.valueOf(11);
    private LottoNumber nineteen = LottoNumber.valueOf(11);
    private LottoNumber twentyone = LottoNumber.valueOf(11);
    private LottoNumber one = LottoNumber.valueOf(1);
    private LottoTickets lottoTickets = new LottoTickets();

    private List<LottoTicket> manualLottoTickets = Arrays.asList(
            new LottoTicket(Arrays.asList(eleven, thirteen, fifteen, seventeen, nineteen, twentyone))
    );

    @Test
    @DisplayName("자동 로또와 수동 로또를 LottoTickets로 합치는 메서드 테스트와" +
            "로또 결과를 반환하는 메서드 테스트")
    void combineTickets_and_matchLottoResult() {
        Payment payment = new Payment(10000);
        TicketInformation ticketInformation = new TicketInformation(payment, 1, manualLottoTickets);

        lottoTickets.combineTickets(ticketInformation);
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(10);


        WinNumber winNumber = new WinNumber(new LottoTicket(
                Arrays.asList(eleven, thirteen, fifteen, seventeen, nineteen, twentyone)));
        BonusBall bonusBall = new BonusBall(winNumber, one);

        LottoResult lottoResult = lottoTickets.matchLottoResult(winNumber, bonusBall);
        assertTrue(lottoResult.rankResult(LottoRank.FIRST) >= 1);
    }
}
