package lotto.controller;

import java.util.List;
import java.util.Scanner;
import lotto.domain.FixedTicketFactory;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningResult;
import lotto.exception.LottoCustomException;
import lotto.utils.ValidateUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    final InputView inputView;

    public LottoController(final Scanner scanner) {
        inputView = new InputView(scanner);
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyTickets(money);

        LottoTicket winningTicket = inputWinningNumbers();
        LottoNumber bonusBall = inputBonus(winningTicket);

        showResult(money, lottoTickets, winningTicket, bonusBall);
    }

    private Money inputMoney() {
        try{
            OutputView.printMoneyMessage();
            Money money = new Money(ValidateUtils.parseInt(inputView.inputValue()));
            OutputView.printTicketCountMessage(money.getTicketCount());
            return money;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private LottoTickets buyTickets(Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeTicketByCount(money.getTicketCount());
        OutputView.printAllTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoTicket inputWinningNumbers() {
        try {
            OutputView.printWinningNumbers();
            return FixedTicketFactory.makeTicket(inputView.inputWinningNumbers());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private LottoNumber inputBonus(LottoTicket winningTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusBall = new LottoNumber(ValidateUtils.parseInt(inputView.inputValue()));
            winningTicket.checkDuplicateNumber(bonusBall);
            return bonusBall;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputBonus(winningTicket);
        }
    }

    private void showResult(Money money, LottoTickets lottoTickets, LottoTicket winningTicket,
        LottoNumber bonusBall) {
        List<Integer> hitCounts = lottoTickets.checkHitCount(winningTicket, bonusBall);
        int totalReward = WinningResult.calculateTotalReward(hitCounts);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.getProfit(totalReward));
        System.out.println(WinningResult.toString(hitCounts));
    }
}
