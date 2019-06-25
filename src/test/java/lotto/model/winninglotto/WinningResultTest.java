package lotto.model.winninglotto;

import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static lotto.model.lotto.LottoNumberRepository.fromNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    List<LottoNumber> lottoNumbers = Arrays.asList(fromNumber(1)
            , fromNumber(2)
            , fromNumber(3)
            , fromNumber(4)
            , fromNumber(5)
            , fromNumber(6));

    @Test
    void 수익률_계산_5등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), fromNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(fromNumber(1)
                , fromNumber(2)
                , fromNumber(3)
                , fromNumber(7)
                , fromNumber(8)
                , fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(500);
    }

    @Test
    void 수익률_계산_4등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), fromNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(fromNumber(1)
                , fromNumber(2)
                , fromNumber(3)
                , fromNumber(4)
                , fromNumber(8)
                , fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(5000);
    }

    @Test
    void 수익률_계산_3등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), fromNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(fromNumber(1)
                , fromNumber(2)
                , fromNumber(3)
                , fromNumber(4)
                , fromNumber(5)
                , fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(150000);
    }

    @Test
    void 수익률_계산_2등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), fromNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(fromNumber(1)
                , fromNumber(2)
                , fromNumber(3)
                , fromNumber(4)
                , fromNumber(5)
                , fromNumber(45));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(Long.valueOf(3000000L));
    }

    @Test
    void 수익률_계산_1등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), fromNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(fromNumber(1)
                , fromNumber(2)
                , fromNumber(3)
                , fromNumber(4)
                , fromNumber(5)
                , fromNumber(6));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(200000000L);
    }
}
