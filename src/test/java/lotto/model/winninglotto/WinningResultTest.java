package lotto.model.winninglotto;

import lotto.model.lotto.BonusNumber;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(1)
            , LottoNumberRepository.fromNumber(2)
            , LottoNumberRepository.fromNumber(3)
            , LottoNumberRepository.fromNumber(4)
            , LottoNumberRepository.fromNumber(5)
            , LottoNumberRepository.fromNumber(6));

    @Test
    void 수익률_계산_5등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(7)
                , LottoNumberRepository.fromNumber(8)
                , LottoNumberRepository.fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(500);
    }

    @Test
    void 수익률_계산_4등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(8)
                , LottoNumberRepository.fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(5000);
    }

    @Test
    void 수익률_계산_3등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(9));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(150000);
    }

    @Test
    void 수익률_계산_2등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(45));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(Long.valueOf(3000000L));
    }

    @Test
    void 수익률_계산_1등() {

        WinningLotto winningLotto = WinningLotto.of(LottoTicket.from(new TreeSet<>(lottoNumbers)), new BonusNumber(45));

        List<LottoNumber> lottoNumber = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(6));
        LottoTickets lottoTickets = LottoTickets.from(Arrays.asList(LottoTicket.from(new TreeSet<>(lottoNumber))));

        assertThat(WinningResult.of(lottoTickets, winningLotto).getROI()).isEqualTo(200000000L);
    }
}
