package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ShuffleNumberGenerator;

public class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    public void setUp() {
        LottoTicket lottoTicket = new LottoTicket(3, new ShuffleNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        this.lottoResult = new LottoResult(lottoTicket, winningLotto);
    }

    @Test
    public void createLottoResult() {
        assertThat(lottoResult).isInstanceOf(LottoResult.class);
    }

    @Test
    public void sumTotalPriceWithoutWinning() {
        long totalPrice = lottoResult.sumTotalPrice();
        assertThat(totalPrice).isEqualTo(0);
    }

    @Test
    public void putRankToLottoResult() {
        LottoTicket lottoTicket = new LottoTicket(1, new AlwaysSameSixNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        this.lottoResult = new LottoResult(lottoTicket, winningLotto);
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }

    @Test
    public void sumTotalPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(1, new AlwaysSameSixNumberGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        this.lottoResult = new LottoResult(lottoTicket, winningLotto);
        assertThat(lottoResult.sumTotalPrice()).isEqualTo(2000000000);
    }
}
