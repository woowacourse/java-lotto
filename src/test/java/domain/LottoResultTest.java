package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("로또 결과 인스턴스 생성 테스트")
    public void createLottoResult() {
        assertThat(lottoResult).isInstanceOf(LottoResult.class);
    }

    @Test
    @DisplayName("꽝 로또에 대한 수익 테스트")
    public void sumTotalPriceWithoutWinningTest() {
        LottoTicket lottoTicket = new LottoTicket(1, new AlwaysSameLastSixNumberGenerator());
        lottoResult = new LottoResult(lottoTicket, winningLotto);
        long totalPrice = lottoResult.sumTotalPrice();
        assertThat(totalPrice).isEqualTo(0);
    }

    @Test
    @DisplayName("1등 로또에 대한 수익 테스트")
    public void sumTotalPriceTest() {
        assertThat(lottoResult.sumTotalPrice()).isEqualTo(2000000000);
    }

    @Test
    @DisplayName("당첨된 로또에 대한 개수 테스트")
    public void resultCountResult() {
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }
}
