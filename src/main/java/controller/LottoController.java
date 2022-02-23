package controller;

import domain.LottoMachine;
import domain.LottoResult;
import domain.WinningLotto;
import java.util.List;
import util.ShuffleNumberGenerator;
import view.OutputView;

public class LottoController {

    private final InputController inputController;

    public LottoController(InputController inputController) {
        this.inputController = inputController;
    }

    public void simulateLotto() {
        int money = inputController.getMoney();
        LottoMachine lottoMachine = new LottoMachine(money, new ShuffleNumberGenerator());
        OutputView.printPurchasedLotto(lottoMachine.getLottoTicket());
        List<Integer> winningLottoNumbers = inputController.getWinningLottoNumbers();
        int bonusLottoNumber = inputController.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusLottoNumber);
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfitRate(lottoMachine.calculateProfit());
    }
}
