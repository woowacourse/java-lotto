package lotto.controller;

import java.util.Scanner;
import java.util.List;
import lotto.domain.FixedTicketFactory;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.WinningResult;
import lotto.utils.ValidateUtils;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    final InputView inputView;

    public LottoController(final Scanner scanner){
        inputView = new InputView(scanner);
    }

    public void run() {
        OutputView.printMoneyMessage();
        Money money = new Money(ValidateUtils.parseInt(inputView.inputValue()));
        OutputView.printTicketCountMessage(money.getTicketCount());

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeTicketByCount(money.getTicketCount());
        OutputView.printAllTickets(lottoTickets);

        OutputView.printWinningNumbers();
        LottoTicket winningTicket = FixedTicketFactory.makeTicket(inputView.inputWinningNumbers());

        OutputView.printBonusNumber();
        LottoNumber bonusBall = new LottoNumber(ValidateUtils.parseInt(inputView.inputValue()));

        List<Integer> hitCounts = lottoTickets.checkHitCount(winningTicket, bonusBall);
        for (int i = 0; i < hitCounts.size(); i++) {
            System.out.println(hitCounts.get(i));
        }
        int totalReward = WinningResult.calculateTotalReward(hitCounts);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.getProfit(totalReward));
        System.out.println(WinningResult.toString(3));
    }
}
