package controller;

import domain.Money;
import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
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
        final Money money = makeMoney();
        final List<Lotto> lottoTickets = makeLottos(money.toLottoCount());
        final WinLotto winLotto = makeWinLotto();

        final Result result = makeResult(lottoTickets, winLotto);
        end(result, money);
    }

    private Money makeMoney() {
        return new Money(InputView.inputMoney());
    }

    private List<Lotto> makeLottos(final int count) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createRandomLotto());
        }
        OutputView.printLottoTickets(lottos);
        return lottos;
    }

    private WinLotto makeWinLotto() {
        List<Integer> winLottoNums = InputView.inputWinLottoNums();
        LottoNumber bonus = LottoNumber.from(InputView.inputBonusNumber());
        return LottoFactory.createWinLotto(winLottoNums, bonus);
    }

    private Result makeResult(final List<Lotto> lottos, WinLotto winLotto) {
        final Result result = new Result();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countSameNum(winLotto);
            boolean isBonus = lotto.isIn(winLotto.getBonus());
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void end(final Result result, final Money money) {
        OutputView.printLottosResult(result);
        OutputView.printProfit((float) result.getPrize() / (float) money.get());
    }
}