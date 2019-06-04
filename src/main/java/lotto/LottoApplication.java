package lotto;

import lotto.domain.game.WinningLotto;
import lotto.domain.game.WinningResult;
import lotto.domain.machine.MoneyProcessor;
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
        VendingMachine vendingMachine = new VendingMachine(createMoneyProcessor());
        LottoTickets lottoTickets = doPurchase(vendingMachine);
        OutputView.printLottoTicketsInformaion(lottoTickets);
        WinningResult winningResult = new WinningResult(lottoTickets,createWinningLotto());
        OutputView.printGameResult(winningResult.getRankInformation(),winningResult.getWinningRate());
    }

    private static LottoTickets doPurchase(final VendingMachine vendingMachine) {
        try {
            int manualNum = getManualNum();
            PurchaseInformation purchaseInformation = vendingMachine.createPurchaseInformation(manualNum, getManualNumbers(manualNum));
            return vendingMachine.createLotto(purchaseInformation);
        } catch (Exception e) {
            System.out.println(e);
            return doPurchase(vendingMachine);
        }
    }

    private static List<List<Integer>> getManualNumbers(int manualNum) {
        try {
            return InputView.getManualNumbers(manualNum);
        } catch (Exception e) {
            System.out.println(e);
            return getManualNumbers(manualNum);
        }
    }

    private static int getManualNum() {
        try {
            return InputView.getManualNum();
        } catch (Exception e) {
            System.out.println(e);
            return getManualNum();
        }
    }

    private static MoneyProcessor createMoneyProcessor() {
        try {
            MoneyProcessor moneyProcessor = MoneyProcessor.of(InputView.getInsertedMoney());
            OutputView.printInsertedMoneyInformation(moneyProcessor.getWholeTicketQuantity(), moneyProcessor.getRest());
            return moneyProcessor;
        } catch (Exception e) {
            System.out.println(e);
            return createMoneyProcessor();
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            List<LottoNumber> winningNumbers = InputView.getWinningNumbers().stream().map(LottoNumber::of).collect(Collectors.toList());
            return WinningLotto.of(winningNumbers,LottoNumber.of(InputView.getBonusNumber()));
        }catch(Exception e){
            System.out.println(e);
            return createWinningLotto();
        }
    }
}
