package lotto.domain.lottoticket;

import lotto.domain.Rank;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
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
        List<LottoNumber> lottoNumbers1 = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(6));
        List<LottoNumber> lottoNumbers2 = Arrays.asList(LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        List<LottoNumber> lottoNumbers3 = Arrays.asList(LottoNumberPool.valueOf(13), LottoNumberPool.valueOf(14)
                , LottoNumberPool.valueOf(15), LottoNumberPool.valueOf(16), LottoNumberPool.valueOf(17)
                , LottoNumberPool.valueOf(18));
        tickets = Arrays.asList(new LottoTicket(lottoNumbers1)
                , new LottoTicket(lottoNumbers2), new LottoTicket(lottoNumbers3));
    }

    @Test
    void 로또_티켓_묶음인_일급컬렉션_생성_후_같은지_비교() {
        assertThat(new LottoTickets(tickets)).isEqualTo(new LottoTickets(tickets));
    }

    @Test
    void 두_티켓_묶음을_static_메서드로_합치기() {
        List<LottoNumber> lottoNumbers4 = Arrays.asList(LottoNumberPool.valueOf(19), LottoNumberPool.valueOf(20)
                , LottoNumberPool.valueOf(21), LottoNumberPool.valueOf(22), LottoNumberPool.valueOf(23)
                , LottoNumberPool.valueOf(24));
        List<LottoNumber> lottoNumbers5 = Arrays.asList(LottoNumberPool.valueOf(25), LottoNumberPool.valueOf(26)
                , LottoNumberPool.valueOf(27), LottoNumberPool.valueOf(28), LottoNumberPool.valueOf(29)
                , LottoNumberPool.valueOf(30));
        List<LottoTicket> tickets2 = Arrays.asList(new LottoTicket(lottoNumbers4)
                , new LottoTicket(lottoNumbers5));

        LottoTickets lottoTickets1 = new LottoTickets(tickets);
        LottoTickets lottoTickets2 = new LottoTickets(tickets2);

        assertThat(LottoTickets.join(lottoTickets1, lottoTickets2).size()).isEqualTo(5);
    }

    @Test
    void 로또_티켓_한_장_추가하기() {
        List<LottoNumber> lottoNumbers4 = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9));
        LottoTicket newTicket = new LottoTicket(lottoNumbers4);

        LottoTickets lottoTickets = new LottoTickets(tickets);
        lottoTickets.add(newTicket);

        assertThat(lottoTickets.size()).isEqualTo(4);
    }

    @Test
    void 로또_티켓의_장_수_체크() {
        assertThat(new LottoTickets(tickets).size()).isEqualTo(3);
    }

    @Test
    void index로_ticket_꺼내기() {
        List<LottoNumber> lottoNumbers2 = Arrays.asList(LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        assertThat(new LottoTickets(tickets).getTicket(1)).isEqualTo(new LottoTicket(lottoNumbers2));
    }

    @Test
    void winningLotto로_LottoResult_생성하기() {
        LottoTickets lottoTickets = new LottoTickets(tickets);

        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9));
        LottoTicket winningTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);

        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);

        LottoResult lottoResult = lottoTickets.countRanksWith(winningLotto, LottoResult.getInitialInstance());

        assertThat(lottoResult.getCountsBy(Rank.FIFTH)).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        tickets = null;
    }
}