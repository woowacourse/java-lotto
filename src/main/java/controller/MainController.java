package controller;

import domain.LottoBall;
import domain.Lotto;
import domain.Money;
import domain.Rank;
import domain.Result;
import domain.WinLotto;
import java.util.ArrayList;
import java.util.List;
import utils.LottoRandomGenerator;
import view.InputView;
import view.OutputView;

public class MainController {

    public void run() {
        Money money = new Money(InputView.inputMoney());
        List<Lotto> lottoTickets = createLottoTickets(money.toLottoCount());
        OutputView.printLottoTickets(lottoTickets);

        WinLotto winLottoNumbers = getWinNumbers();

        Result result = makeResult(lottoTickets, winLottoNumbers);
        printResult(result, money);
    }

    private List<Lotto> createLottoTickets(int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(Lotto.fromBalls(LottoRandomGenerator.generate()));
        }
        return lottoTickets;
    }

    private WinLotto getWinNumbers() {
        List<Integer> winLottoNumber = InputView.inputWinLottoNumbers();
        LottoBall bonus = LottoBall.from(InputView.inputBonusNumber());
        return Lotto.toWinLotto(winLottoNumber, bonus);
    }

    private Result makeResult(List<Lotto> lottoTickets, WinLotto winLottoNumbers) {
        Result result = new Result();
        for (Lotto lottoTicket : lottoTickets) {
            int matchCount = lottoTicket.countSameNumber(winLottoNumbers);
            boolean isBonus = lottoTicket.isIn(winLottoNumbers.getBonus());
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void printResult(Result result, Money money) {
        OutputView.printResult(result);
        OutputView.printProfit((float)result.getPrice()/ (float) money.get());
    }
}