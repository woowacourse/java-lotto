package controller;

import domain.Money;
import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicketCount;
import domain.lotto.WinNumbers;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import utils.NumsGenerator;
import utils.Util;
import view.InputView;
import view.OutputView;

public class MainController {
    public void run() {
        final Money money = makeMoney();
        final LottoTicketCount count = LottoTicketCount.of(money.toLottoCount(), InputView.inputManualTicketCount());
        final List<Lotto> lottoTickets = makeLottos(count);
        final WinNumbers winNumbers = makeWinNums();

        final Result result = new Result(lottoTickets, winNumbers);
        end(result, money);
    }

    private Money makeMoney() {
        return new Money(InputView.inputMoney());
    }

    private List<Lotto> makeLottos(final LottoTicketCount count) {
        final List<Lotto> lottos = new ArrayList<>();
        addManualLottos(lottos, count.getManualTicketCount());
        addAutoLottos(lottos, count.getAutoTicketCount());
        OutputView.printLottoTickets(lottos);
        return lottos;
    }

    private void addManualLottos(List<Lotto> lottos, final int count) {
        final List<List<Integer>> manualLottoNumsGroup = InputView.inputManualTicketGroup(count);
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createLotto(NumsGenerator.generate(manualLottoNumsGroup.get(i))));
        }
    }

    private void addAutoLottos(List<Lotto> lottos, final int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createLotto(NumsGenerator.generateByRandom()));
        }
    }

    private WinNumbers makeWinNums() {
        List<Integer> winLottoRawNums = InputView.inputWinLottoNums();
        LottoNumber bonus = LottoNumber.from(InputView.inputBonusNumber());
        return LottoFactory.createWinNums(NumsGenerator.generate(winLottoRawNums), bonus);
    }

    private void end(final Result result, final Money money) {
        OutputView.printLottosResult(result);
        OutputView.printProfit(Util.getProfit((float) result.getPrize(), (float) money.get()));
    }
}