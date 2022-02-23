package controller;

import domain.LottoNumbers;
import domain.Money;
import domain.Rank;
import domain.Result;
import domain.WinLottoNumbers;
import java.util.ArrayList;
import java.util.List;
import utils.LottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class MainController {

    public void run() {
        Money money = getMoney();
        List<LottoNumbers> lottoTickets = createLottoTickets(money.toLottoCount());
        OutputView.printLottoTickets(lottoTickets);

        WinLottoNumbers winLottoNumbers = getWinNumbers();

        Result result = makeResult(lottoTickets, winLottoNumbers);
        printResult(result, money);
    }

    private Money getMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getMoney();
        }
    }

    private List<LottoNumbers> createLottoTickets(int count) {
        List<LottoNumbers> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new LottoNumbers(LottoNumberGenerator.generate()));
        }
        return lottoTickets;
    }

    private WinLottoNumbers getWinNumbers() {
        try {
            String winLottoNumber = InputView.inputWinLottoNumbers();
            int bonus = InputView.inputBonusNumber();
            return WinLottoNumbers.of(winLottoNumber, bonus);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getWinNumbers();
        }
    }

    private Result makeResult(List<LottoNumbers> lottoTickets, WinLottoNumbers winLottoNumbers) {
        Result result = new Result();
        for (LottoNumbers lottoTicket : lottoTickets) {
            int matchCount = lottoTicket.countSameNumber(winLottoNumbers);
            boolean isBonus = lottoTicket.isContainsBonus(winLottoNumbers.getBonus());
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void printResult(Result result, Money money) {
        OutputView.printResult(result);
        OutputView.printProfit((float) result.getPrice() / (float) money.get());
    }
}