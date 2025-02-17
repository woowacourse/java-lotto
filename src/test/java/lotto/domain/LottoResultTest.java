package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    private static LottoTickets lottoTickets;
    private static LottoMoney lottoMoney;

    @BeforeAll
    static void setUp() {
        lottoMoney = new LottoMoney(1000);
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(12345L);
        LottoMachine lottoMachine = new LottoMachine(lottoNumberGenerator);
        lottoTickets = new LottoTickets();
        for (Lotto lottoTicket : lottoMachine.generateLottoTickets(lottoMoney)) {
            lottoTickets.addLottoTicket(lottoTicket);
        }
    }

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus True")
    @Test
    public void lottoMatch_5Hit_BonusTrue() {
        Lotto winningNumbers = new Lotto("5, 7, 19, 26, 32, 44");
        WinningLotto winningLotto = new WinningLotto(winningNumbers, "41");

        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(1, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 일치 개수 확인 - 5개 Hit, Bonus False")
    @Test
    public void lottoMatch_5Hit_BonusFalse() {
        Lotto winningNumbers = new Lotto("5, 7, 19, 26, 32, 44");
        WinningLotto winningLotto = new WinningLotto(winningNumbers, "11");

        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(1, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(0, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 일치 개수 확인 - 2개 Hit (3개 Hit 미만)")
    @Test
    public void lottoMatch_Under3Hit() {
        Lotto winningNumbers = new Lotto("5, 7, 29, 6, 3, 4");
        WinningLotto winningLotto = new WinningLotto(winningNumbers, "11");

        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        Map<LottoPrize, Integer> matchResult = lottoResult.getLottoResult();

        assertEquals(0, (int) matchResult.get(LottoPrize.FIFTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.FOURTH));
        assertEquals(0, (int) matchResult.get(LottoPrize.THIRD));
        assertEquals(0, (int) matchResult.get(LottoPrize.SECOND));
        assertEquals(0, (int) matchResult.get(LottoPrize.FIRST));
    }

    @DisplayName("로또 결과 수익률 확인")
    @Test
    public void lottoProfitRate() {
        Lotto winningNumbers = new Lotto("5, 7, 19, 26, 32, 44");
        WinningLotto winningLotto = new WinningLotto(winningNumbers, "11");

        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);
        assertEquals((double) 1_500_000 / 1_000, lottoResult.calculateLottoProfitRate(lottoMoney));
    }
}
