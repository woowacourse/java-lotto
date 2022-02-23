package controller;

import domain.LottoNumbers;
import domain.Money;
import java.util.ArrayList;
import java.util.List;
import utils.LottoNumberGenerator;
import view.InputView;

public class MainController {
    public void run() {
        // money
        Money money = new Money(InputView.inputMoney());

        // create lotto
        List<LottoNumbers> lottoTickets = createLottoTickets(money.toLottoCount());

        // print lotto
        // OutputView.printLottoTickets();

        // lotto numbers
        // bonus number
        // result
    }

    private List<LottoNumbers> createLottoTickets(int count) {
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(new LottoNumbers(LottoNumberGenerator.generate()));
        }
        return lottoNumbers;
    }
}
