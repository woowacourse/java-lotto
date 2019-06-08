package lotto;

import lotto.domain.game.WinningLotto;
import lotto.domain.game.WinningResult;
import lotto.domain.machine.Money;
import lotto.domain.machine.PurchaseInformation;
import lotto.domain.machine.VendingMachine;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = Money.of(InputView.getInsertedMoney());
        OutputView.printInsertedMoneyInformation(money.getWholeTicketQuantity(), money.getRest());
        VendingMachine vendingMachine = new VendingMachine(money);

        int manualNum = InputView.getManualNum();
        PurchaseInformation purchaseInformation = vendingMachine.createPurchaseInformation(manualNum, InputView.getManualNumbers(manualNum));

        LottoTickets lottoTickets = vendingMachine.createLotto(purchaseInformation);
        OutputView.printLottoTicketsInformaion(lottoTickets);

        List<LottoNumber> winningNumbers = InputView.getWinningNumbers().stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        WinningResult winningResult = new WinningResult(lottoTickets, WinningLotto.of(winningNumbers, LottoNumber.of(InputView.getBonusNumber())));
        OutputView.printGameResult(winningResult.getRankInformation(), winningResult.getWinningRate());
    }
}
