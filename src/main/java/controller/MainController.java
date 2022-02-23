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
        // money
        Money money = new Money(InputView.inputMoney());

        // create lotto
        List<LottoNumbers> lottoTickets = createLottoTickets(money.toLottoCount());

        // print lotto
        OutputView.printLottoTickets(lottoTickets);

        // lotto numbers
        String winLottoNumber = InputView.inputWinLottoNumbers();

        // bonus number
        int bonus = InputView.inputBonusNumber();
        WinLottoNumbers winLottoNumbers = WinLottoNumbers.of(winLottoNumber, bonus);

        // result
        OutputView.printResult(makeResult(lottoTickets, winLottoNumbers));
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

    private List<LottoNumbers> createLottoTickets(int count) {
        List<LottoNumbers> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new LottoNumbers(LottoNumberGenerator.generate()));
        }
        return lottoTickets;
    }
}