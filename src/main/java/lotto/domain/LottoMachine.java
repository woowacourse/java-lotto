package lotto.domain;

import java.util.List;

public class LottoMachine {

    private LottoMachine() {
    }

    public static Money initMoney(int moneyAmount) {
        return new Money(moneyAmount);
    }

    public static Lottos initLottos(List<List<Integer>> manualNumbers, LottoBuyCount lottoBuyCount) {
        return new Lottos(manualNumbers, lottoBuyCount.getAutoCount());
    }

    public static WinningNumber initWinningNumber(List<Integer> winningLotto, int bonusNumber) {
        return new WinningNumber(new Lotto(winningLotto), new LottoNumber(bonusNumber));
    }
}
