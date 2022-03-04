package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

import static domain.LottoMachine.LOTTO_TICKET_PRICE;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = purchaseLottos(lottoMachine);
        createResult(lottoMachine, lottos);
    }

    private Lottos purchaseLottos(LottoMachine lottoMachine) {
        Money money = new Money(InputView.getMoney());
        PurchaseCount purchaseCount = PurchaseCount.from(money, InputView.getManualPurchaseCount());
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(purchaseCount);
        Lottos lottos = lottoMachine.purchase(manualLottoNumbers, purchaseCount);
        OutputView.printPurchasedLottoTicketNumber(purchaseCount);
        OutputView.printPurchasedLottoTickets(lottos);

        return lottos;
    }

    private void createResult(LottoMachine lottoMachine, Lottos lottos) {
        LottoNumbers winningNumbers = LottoNumbers.convertToLottoNumber(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        WinningStat winningStat = lottoMachine.createWinningStat(lottos, winningLotto);
        OutputView.printWinningStat(winningStat, winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
