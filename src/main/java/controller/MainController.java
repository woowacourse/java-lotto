package controller;

import domain.LottoNumbers;
import java.util.List;
import view.InputView;

public class MainController {
    public void run() {
        // money
        int money = InputView.inputMoney();

        // create lotto
        List<LottoNumbers> lottoTickets = createLottoTickets(money);

        // print lotto
        // OutputView.printLottoTickets();
        // lotto numbers
        // bonus number
        // result
    }

    private List<LottoNumbers> createLottoTickets(int money) {
        return null;
    }
}
