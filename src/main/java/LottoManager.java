import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public List<Lotto> generateLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(Number.MIN, Number.MAX, 6)));
        }
        return lottos;
    }

    public WinningResult getWinningResult(List<Lotto> lottos, WinningLotto winningLotto) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottos) {
            LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);
            winningResult.increaseCount(lottoPrize, 1);
        }
        return winningResult;
    }

    public float getRevenue(WinningResult winningResult, Money money) {
        return (float) winningResult.getTotalPrices() / money.getValue();
    }
}
