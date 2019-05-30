package lotto.model.winninglotto;

import lotto.model.lotto.BonusNumber;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatisticsTest {
    List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
            , new LottoNumber(2)
            , new LottoNumber(3)
            , new LottoNumber(4)
            , new LottoNumber(5)
            , new LottoNumber(6));

    @Test
    void 수익률_계산_5등() {

        WinningLotto winningLotto = new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(7)
                , new LottoNumber(8)
                , new LottoNumber(9));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new TreeSet<>(lottoNumber))));

        assertThat(new WinningStatistics(lottoTickets, winningLotto).calculateROI()).isEqualTo(500);
    }

    @Test
    void 수익률_계산_4등() {

        WinningLotto winningLotto = new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(8)
                , new LottoNumber(9));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new TreeSet<>(lottoNumber))));

        assertThat(new WinningStatistics(lottoTickets, winningLotto).calculateROI()).isEqualTo(5000);
    }

    @Test
    void 수익률_계산_3등() {

        WinningLotto winningLotto = new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(9));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new TreeSet<>(lottoNumber))));

        assertThat(new WinningStatistics(lottoTickets, winningLotto).calculateROI()).isEqualTo(150000);
    }

    @Test
    void 수익률_계산_2등() {

        WinningLotto winningLotto = new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(45));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new TreeSet<>(lottoNumber))));

        assertThat(new WinningStatistics(lottoTickets, winningLotto).calculateROI()).isEqualTo(Long.valueOf(3000000L));
    }

    @Test
    void 수익률_계산_1등() {

        WinningLotto winningLotto = new WinningLotto(new LottoTicket(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(6));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new TreeSet<>(lottoNumber))));

        assertThat(new WinningStatistics(lottoTickets, winningLotto).calculateROI()).isEqualTo(200000000L);
    }
}
