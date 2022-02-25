package controller;

import domain.LottoTicket;
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
        List<LottoTicket> lottoTickets = createLottoTickets(money.toLottoCount());
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

    private List<LottoTicket> createLottoTickets(int count) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new LottoTicket(LottoNumberGenerator.generate()));
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

    private Result makeResult(List<LottoTicket> lottoTickets, WinLottoNumbers winLottoNumbers) {
        Result result = new Result();
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchCount = winLottoNumbers.countSameNumber(lottoTicket);
            boolean isBonus = winLottoNumbers.isContainsBonus(lottoTicket);
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void printResult(Result result, Money money) {
        OutputView.printResult(result);
        OutputView.printProfit((float) result.getPrice() / (float) money.get());
    }
}