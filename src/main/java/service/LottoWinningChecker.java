package service;

import domain.Lotto;
import domain.LottoRanking;
import domain.LottoResult;
import domain.Lottos;
import domain.WinningNumberWithBonusNumber;

import java.util.ArrayList;
import java.util.List;

public class LottoWinningChecker {

    public LottoResult calculateResult(Lottos lottos, WinningNumberWithBonusNumber winningNumberWithBonusNumber) {
        LottoResult lottoResult = LottoResult.initialize();

        lottos.getLottos().forEach(
                lotto -> lottoResult.add(check(lotto, winningNumberWithBonusNumber)));

        return lottoResult;
    }


    private LottoRanking check(Lotto lotto, WinningNumberWithBonusNumber winningNumberWithBonusNumber) {
        List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
        numbers.retainAll(winningNumberWithBonusNumber.winningNumber().getNumbers());

        int correctCount = numbers.size();
        boolean isBonusCollect = lotto.getNumbers().contains(winningNumberWithBonusNumber.bonusNumber());
        return LottoRanking.from(correctCount, isBonusCollect);
    }
}
