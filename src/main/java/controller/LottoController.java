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
        LottoMachine lottoMachine = createLottoMachine();
        WinningLotto winningLotto = requestWinningLotto();
        announceResult(lottoMachine, winningLotto);
    }

    private LottoMachine createLottoMachine() {
        int money = inputController.getMoney();
        LottoMachine lottoMachine = new LottoMachine(money, new ShuffleNumberGenerator());
        OutputView.printPurchasedLotto(lottoMachine.getLottoTicket());
        return lottoMachine;
    }

    private WinningLotto requestWinningLotto() {
        List<Integer> winningLottoNumbers = inputController.getWinningLottoNumbers();
        int bonusLottoNumber = inputController.getBonusNumber();
        return new WinningLotto(winningLottoNumbers, bonusLottoNumber);
    }

    private void announceResult(LottoMachine lottoMachine, WinningLotto winningLotto) {
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfitRate(lottoMachine.calculateProfit());
    }
}
