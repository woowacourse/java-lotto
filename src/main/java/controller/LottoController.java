package controller;

import domain.*;
import domain.strategy.RandomLottoNumberStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

import static domain.LottoMachine.LOTTO_TICKET_PRICE;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoNumbers> lottos = purchaseLottoTickets(lottoMachine);
        createResult(lottoMachine, lottos);
    }

    private List<LottoNumbers> purchaseLottoTickets(LottoMachine lottoMachine) {
        Money money = new Money(InputView.getMoney());
        PurchaseType purchaseType = new PurchaseType(money, InputView.getManualPurchaseCount());
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(purchaseType);
        List<LottoNumbers> lottos = lottoMachine.purchaseLottoTickets(manualLottoNumbers, purchaseType,
                new RandomLottoNumberStrategy());

        OutputView.printPurchasedLottoTicketNumber(purchaseType);
        OutputView.printPurchasedLottoTickets(lottos);

        return lottos;
    }

    private void createResult(LottoMachine lottoMachine, List<LottoNumbers> lottos) {
        LottoNumbers winningNumbers = LottoNumbers.convertToLottoNumber(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);
        WinLotto winLotto = new WinLotto(winningNumbers, bonusNumber);

        WinningStat winningStat = lottoMachine.createWinningStat(lottos, winLotto);
        OutputView.printWinningStat(winningStat, winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
