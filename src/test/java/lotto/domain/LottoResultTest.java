package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus True")
    @Test
    public void lottoMatch_5Hit_BonusTrue() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Lotto> lottoTickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)
        ));

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);

        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertThat(matchResult.get(LottoPrize.MISS)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FIFTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FOURTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.THIRD)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.SECOND)).isEqualTo(1);
        assertThat(matchResult.get(LottoPrize.FIRST)).isEqualTo(0);
    }

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus False")
    @Test
    public void lottoMatch_5Hit_BonusFalse() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Lotto> lottoTickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 8)
        ));

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);

        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertThat(matchResult.get(LottoPrize.MISS)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FIFTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FOURTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.THIRD)).isEqualTo(1);
        assertThat(matchResult.get(LottoPrize.SECOND)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FIRST)).isEqualTo(0);
    }

    @DisplayName("로또 결과 일치 개수 확인 - 2개 Hit (3개 Hit 미만)")
    @Test
    public void lottoMatch_Under3Hit() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Lotto> lottoTickets = List.of(
                new Lotto(List.of(1, 2, 8, 9, 10, 11)
        ));

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);

        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertThat(matchResult.get(LottoPrize.MISS)).isEqualTo(1);
        assertThat(matchResult.get(LottoPrize.FIFTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FOURTH)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.THIRD)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.SECOND)).isEqualTo(0);
        assertThat(matchResult.get(LottoPrize.FIRST)).isEqualTo(0);
}

    @DisplayName("로또 결과 수익률 확인")
    @Test
    public void lottoProfitRate() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Lotto> lottoTickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 8)
        ));

        LottoMoney lottoMoney = new LottoMoney("10000");

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);

        assertThat(lottoResult.getLottoProfitRate()).isEqualTo((double) 1_500_000 / 10_000);
    }
}
