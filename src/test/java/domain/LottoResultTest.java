package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    private LottoTicket lottoTicket;
    private WinningLotto winningLotto;
    private LottoResult lottoResult;

    @BeforeEach
    public void setUp() {
        lottoTicket = new LottoTicket(1, new AlwaysSameSixNumberGenerator());
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = new LottoResult(lottoTicket, winningLotto);
    }

    @Test
    public void createLottoResult() {
        assertThat(lottoResult).isInstanceOf(LottoResult.class);
    }

    @Test
    public void sumTotalPriceWithoutWinningTest() {
        LottoTicket lottoTicket = new LottoTicket(1, new AlwaysSameLastSixNumberGenerator());
        lottoResult = new LottoResult(lottoTicket, winningLotto);
        long totalPrice = lottoResult.sumTotalPrice();
        assertThat(totalPrice).isEqualTo(0);
    }

    @Test
    public void sumTotalPriceTest() {
        assertThat(lottoResult.sumTotalPrice()).isEqualTo(2000000000);
    }

    @Test
    public void resultCountResult() {
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }
}
