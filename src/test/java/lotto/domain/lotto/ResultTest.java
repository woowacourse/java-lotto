package lotto.domain.lotto;

import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.paymentinfo.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));

        lottoTickets = new LottoTickets(lottoRepository);

        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6)));

        winningLotto = new WinningLotto(lotto, LottoNumber.getNumber(7));
    }

    @Test
    void Rank에_따른_당첨_개수_확인() {
        Result result = new Result(winningLotto, lottoTickets);
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 해당_Rank가_당첨되지_않은_경우() {
        Result result = new Result(winningLotto, lottoTickets);

        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.MISS)).isEqualTo(0);
    }

    @Test
    void 수익률_계산() {
        Result result = new Result(winningLotto, lottoTickets);

        assertThat(result.calculateEarningsRate(new Payment(10_000))).isEqualTo(200_000);
    }
}
