import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

import controller.dto.LottoDtoMapper;
import controller.dto.LottoRankResultResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.LottoRank;
import model.LottoRankFinder;
import model.LottoStore;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    private LottoStore lottoStore = new LottoStore(
            () -> List.of(1, 2, 3, 4, 5, 6),
            new LottoRankFinder(),
            new LottoDtoMapper());

    @Test
    void 구입_금액이_1000원_단위가_아나라면_예외를_발생시킨다() {
        int purchaseAmount = 1001;
        assertThatThrownBy(
                () -> lottoStore.calculatePurchaseCount(purchaseAmount)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액에_따라_구매될_로또_개수를_반환한다() {
        int purchaseAmount = 14000;

        int purchaseCount = lottoStore.calculatePurchaseCount(purchaseAmount);
        assertThat(purchaseCount).isEqualTo(14);
    }

    @Test
    void 구입_개수만큼_로또_티켓이_생성된다() {
        int purchaseCount = 14;
        assertThat(lottoStore.createLottoTickets(purchaseCount).size()).isEqualTo(14);
        assertThat(lottoStore.createLottoTickets(purchaseCount).getFirst().numbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 당첨결과_개수를_센다() {
        // given
        List<LottoTicketResponse> lottoTickets = List.of(new LottoTicketResponse(List.of(1, 2, 3, 4, 5, 6)));
        WinningLottoRequest winningLotto = new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 7), 6);

        // when
        LottoRankResultResponse lottoRankResultResponse = lottoStore.countLottoRankResult(lottoTickets, winningLotto);

        // then
        assertThat(lottoRankResultResponse.getValue(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    void 수익률을_계산한다() {
        // given
        int ticketCount = 14;

        Map<LottoRank, Integer> data = new HashMap<>();
        data.put(LottoRank.FIFTH, 1);
        LottoRankResultResponse lottoRankResultResponse = new LottoRankResultResponse(data);

        // when
        double profitRate = lottoStore.calculateProfitRate(ticketCount, lottoRankResultResponse);

        // then
        assertThat(profitRate).isCloseTo(0.36, within(0.01));
    }

}
