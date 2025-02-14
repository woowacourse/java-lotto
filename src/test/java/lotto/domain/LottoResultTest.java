package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus True")
    @Test
    public void lottoMatch_5Hit_BonusTrue() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Set<Integer>> lottoTickets = List.of(
                Set.of(1, 2, 3, 4, 5, 7)  // 5 hit, bonus true
        );

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(0, (int) matchResult.get(LottoPrize.MISS));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(1, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus False")
    @Test
    public void lottoMatch_5Hit_BonusFalse() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Set<Integer>> lottoTickets = List.of(
                Set.of(1, 2, 3, 4, 5, 8)  // 5 hit, bonus false
        );

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(0, (int) matchResult.get(LottoPrize.MISS));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(1, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(0, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 일치 개수 확인 - 2개 Hit (3개 Hit 미만)")
    @Test
    public void lottoMatch_Under3Hit() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Set<Integer>> lottoTickets = List.of(
                Set.of(1, 2, 8, 9, 10, 11)  // 2 hit
        );

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(1, (int) matchResult.get(LottoPrize.MISS));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(0, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 수익률 확인")
    @Test
    public void lottoProfitRate() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        WinningLotto winningLotto = new WinningLotto(lotto, "7");
        List<Set<Integer>> lottoTickets = List.of(
                Set.of(1, 2, 3, 4, 5, 8)  // 5 hit, bonus false
        );

        LottoMoney lottoMoney = new LottoMoney("10000");
        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        lottoResult.calculateLottoProfitRate(lottoMoney);
        assertEquals((double) 1_500_000 / 10_000, lottoResult.getLottoProfitRate());
    }
}
