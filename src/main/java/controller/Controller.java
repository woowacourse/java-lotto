package controller;

import domain.Lotto;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        int amount = InputView.requestAmount();
        Lotto lotto = new Lotto(amount);

        OutputView.printTicketCount(lotto.getTicketCount());
        OutputView.printTickets(lotto.getTickets());
    }
}
