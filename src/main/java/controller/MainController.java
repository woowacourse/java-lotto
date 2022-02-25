package controller;

import domain.Money;
import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoFactory;
import domain.lotto.WinNumbers;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import utils.NumsGenerator;
import view.InputView;
import view.OutputView;

public class MainController {
    public void run() {
        final Money money = makeMoney();
        final List<Lotto> lottoTickets = makeLottos(money.toLottoCount());
        final WinNumbers winNumbers = makeWinNums();

        final Result result = new Result(lottoTickets, winNumbers);
        runOutputView(result, money);
    }

    private Money makeMoney() {
        return new Money(InputView.inputMoney());
    }

    private List<Lotto> makeLottos(final int count) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createLotto(NumsGenerator.generateByRandom()));
        }
        OutputView.printLottoTickets(lottos);
        return lottos;
    }

    private WinNumbers makeWinNums() {
        List<Integer> winLottoRawNums = InputView.inputWinLottoNums();
        LottoNumber bonus = LottoNumber.from(InputView.inputBonusNumber());
        return LottoFactory.createWinNums(NumsGenerator.generate(winLottoRawNums), bonus);
    }

    private void runOutputView(final Result result, final Money money) {
        OutputView.printLottosResult(result);
        OutputView.printProfit((float) result.getPrize() / (float) money.get());
    }
}