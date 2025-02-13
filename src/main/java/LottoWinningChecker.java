import java.util.ArrayList;
import java.util.List;

public class LottoWinningChecker {
    private LottoWinningChecker() {}

    public static LottoResult calculateResult(Lottos lottos, WinningNumberWithBonusNumber winningNumberWithBonusNumber) {
        LottoResult lottoResult = LottoResult.initialize();

        lottos.getLottos().forEach(
                lotto -> {
                    lottoResult.add(check(lotto, winningNumberWithBonusNumber));
                }
        );

        return lottoResult;
    }


    private static LottoRanking check(Lotto lotto, WinningNumberWithBonusNumber winningNumberWithBonusNumber) {
        List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
        numbers.retainAll(winningNumberWithBonusNumber.winningNumber().getNumbers());

        int collectCount = numbers.size();
        boolean isBonusCollect = lotto.getNumbers().contains(winningNumberWithBonusNumber.bonusNumber());
        return LottoRanking.from(collectCount, isBonusCollect);
    }
}
