import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import model.LottoNumberGenerator;
import model.LottoRank;
import model.LottoRankCalculator;
import model.LottoRankResult;
import model.LottoStore;
import model.LottoTicket;
import model.WinningLotto;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    private LottoStore lottoStore = new LottoStore(new LottoNumberGenerator(), new LottoRankCalculator());

    @Test
    void 구입_금액이_1000원_단위가_아나라면_예외를_발생시킨다() {
        int purchasePrice = 1001;
        assertThatThrownBy(
                () -> lottoStore.purchase(purchasePrice)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액에_해당하는_개수만큼_로또_티켓이_생성된다() {
        int purchasePrice = 14000;
        assertThat(lottoStore.purchase(purchasePrice).size()).isEqualTo(14);
    }

    @Test
    void 당첨결과_개수를_센다() {
        // given
        List<LottoTicket> lottoTickets = List.of(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 7), 6);

        // when
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoTickets, winningLotto);

        // then
        assertThat(lottoRankResult.getValue(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    void 수익률을_계산한다() {
        // given
        int ticketCount = 14;
        LottoRankResult lottoRankResult = new LottoRankResult();
        lottoRankResult.updateRankCount(LottoRank.FIFTH);

        // when
        double profitRate = lottoStore.calculateProfitRate(ticketCount, lottoRankResult);

        // then
        assertThat(profitRate).isCloseTo(0.36, within(0.01));
    }

}
