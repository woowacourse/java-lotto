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
import static org.assertj.core.api.Assertions.offset;

class LottoResultTest {
    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        List<LottoNumber> lottoNumbers2 = Arrays.asList(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
                , new LottoNumber(10), new LottoNumber(11), new LottoNumber(12));
        List<LottoNumber> lottoNumbers3 = Arrays.asList(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15)
                , new LottoNumber(16), new LottoNumber(17), new LottoNumber(18));
        List<LottoTicket> tickets = Arrays.asList(new LottoTicket(lottoNumbers1)
                , new LottoTicket(lottoNumbers2), new LottoTicket(lottoNumbers3));

        List<LottoNumber> winningLottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(10), new LottoNumber(11), new LottoNumber(12));
        LottoTicket winningLotto = new LottoTicket(winningLottoNumbers);

        LottoNumber bonusBall = new LottoNumber(4);

        lottoResult = LottoResult.of(new LottoTickets(tickets), winningLotto, bonusBall);
    }

    @Test
    void LottoResult_getter_체크() {
        assertThat(lottoResult.getCountsBy(Rank.FIFTH)).isEqualTo(2);
    }

    @Test
    void 수익률_계산하기() {
        assertThat(lottoResult.getProfitRatio()).isEqualTo(3.333, offset(0.00099));
    }

    @AfterEach
    void tearDown() {
        lottoResult = null;
    }
}