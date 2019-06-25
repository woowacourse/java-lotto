package lotto;

import lotto.domain.game.WinningLotto;
import lotto.domain.game.WinningResult;
import lotto.domain.machine.Money;
import lotto.domain.machine.Purchase;
import lotto.domain.machine.VendingMachine;
import lotto.domain.machine.exeption.InvalidManualNumException;
import lotto.domain.machine.exeption.InvalidMinimumMoneyException;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {

        Money money = insertMoney(InputView.getInsertedMoney());
        OutputView.printInsertedMoneyInformation(money.ticketQuantity(), money.getRest());

        VendingMachine vendingMachine = new VendingMachine(money);

        Purchase purchase = doPurchase(vendingMachine);
        LottoTickets lottoTickets = makeLotto(vendingMachine, purchase);

        OutputView.printLottoTicketsInformaion(lottoTickets);

        List<LottoNumber> winningNumbers = InputView.getWinningNumbers().stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        WinningResult winningResult = new WinningResult(lottoTickets, getWinningLotto(winningNumbers, LottoNumber.of(InputView.getBonusNumber())));

        OutputView.printGameResult(winningResult.getRankInformation(), winningResult.getWinningRate());
    }

    private static WinningLotto getWinningLotto(final List<LottoNumber> winningNumbers, final LottoNumber lottoNumber) {
        try {
            return WinningLotto.of(winningNumbers, lottoNumber);
        } catch (Exception e) {
            return getWinningLotto(winningNumbers, lottoNumber);
        }
    }

    private static Money insertMoney(int moneyAmount) {
        try {
            return Money.of(moneyAmount);
        } catch (InvalidMinimumMoneyException e) {
            return insertMoney(moneyAmount);
        }
    }

    private static Purchase doPurchase(VendingMachine vendingMachine) {
        try {
            int manualTicketQuantity = InputView.getManualTicketQuantity();
            return vendingMachine.createPurchase(manualTicketQuantity, InputView.getManualNumbers(manualTicketQuantity));
        } catch (InvalidManualNumException e) {
            System.out.println(e);
            return doPurchase(vendingMachine);
        } catch (Exception e) {
            System.out.println(e);
            return doPurchase(vendingMachine);
        }
    }


    private static LottoTickets makeLotto(final VendingMachine vendingMachine, final Purchase purchase) {
        try {
            return vendingMachine.createLotto(purchase);
        } catch (RuntimeException e) {
            System.out.println(e);
            return makeLotto(vendingMachine, purchase);
        } catch (Exception e) {
            System.out.println(e);
            return makeLotto(vendingMachine, purchase);
        }
    }


}
