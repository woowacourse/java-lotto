package controller;

import domain.Money;
import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoFactory;
import domain.lotto.WinLotto;
import domain.result.Rank;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {
    public void run() {
        Money money = makeMoney();
        List<Lotto> lottoTickets = makeLottos(money.toLottoCount());
        WinLotto winLottoNumbers = makeWinLotto();

        Result result = makeResult(lottoTickets, winLottoNumbers);
        end(result, money);
    }

    private Money makeMoney() {
        return new Money(InputView.inputMoney());
    }

    private List<Lotto> makeLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createRandomLotto());
        }
        OutputView.printLottoTickets(lottos);
        return lottos;
    }

    private WinLotto makeWinLotto() {
        List<Integer> winLottoNums = InputView.inputWinLottoNums();
        LottoBall bonus = LottoBall.from(InputView.inputBonusNumber());
        return LottoFactory.createWinLotto(winLottoNums, bonus);
    }

    private Result makeResult(List<Lotto> lottos, WinLotto winLotto) {
        Result result = new Result();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countSameNum(winLotto);
            boolean isBonus = lotto.isIn(winLotto.getBonus());
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void end(Result result, Money money) {
        OutputView.printLottosResult(result);
        OutputView.printProfit((float) result.getPrize() / (float) money.get());
    }
}