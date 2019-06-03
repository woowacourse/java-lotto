package lotto.domain;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    private List<LottoTicket> tickets;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        List<LottoNumber> lottoNumbers2 = Arrays.asList(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
                , new LottoNumber(10), new LottoNumber(11), new LottoNumber(12));
        List<LottoNumber> lottoNumbers3 = Arrays.asList(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15)
                , new LottoNumber(16), new LottoNumber(17), new LottoNumber(18));
        tickets = Arrays.asList(new LottoTicket(lottoNumbers1)
                , new LottoTicket(lottoNumbers2), new LottoTicket(lottoNumbers3));
    }

    @Test
    void 로또_티켓_묶음인_일급컬렉션_생성_후_같은지_비교() {
        assertThat(new LottoTickets(tickets)).isEqualTo(new LottoTickets(tickets));
    }

    @Test
    void 로또_티켓의_장_수_체크() {
        assertThat(new LottoTickets(tickets).size()).isEqualTo(3);
    }

    @Test
    void index로_ticket_꺼내기() {
        List<LottoNumber> lottoNumbers2 = Arrays.asList(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
                , new LottoNumber(10), new LottoNumber(11), new LottoNumber(12));
        assertThat(new LottoTickets(tickets).getTicket(1)).isEqualTo(new LottoTicket(lottoNumbers2));
    }

    @Test
    void winningLotto로_lottoResult_생성하기() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(7), new LottoNumber(8), new LottoNumber(9));
        LottoTicket winningLotto = new LottoTicket(lottoNumbers);
        LottoResult lottoResult = LottoResult.of(new LottoTickets(tickets), winningLotto);

        assertThat(lottoResult.getCountsBy(Rank.FIFTH)).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        tickets = null;
    }
}