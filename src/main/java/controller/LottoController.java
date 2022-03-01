package controller;

import domain.*;
import util.ShuffleNumberGenerator;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

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

    private List<Lotto> getPassiveLottos() {
        List<Lotto> passiveLottos = new ArrayList<>();
        int passiveLottoCount = inputController.getPassiveLottoCount();
        OutputView.printPassiveLottoInputGuide();
        for (int i = 0; i < passiveLottoCount; i++) {
            passiveLottos.add(new PassiveLotto(inputController.getPassiveLottoNumbers()));
        }
        return passiveLottos;
    }

    private LottoMachine createLottoMachine() {
        int money = inputController.getMoney();
        List<Lotto> passiveLottos = getPassiveLottos();
        LottoMachine lottoMachine = new LottoMachine(money, passiveLottos, new ShuffleNumberGenerator());
        OutputView.printPurchasedLotto(lottoMachine.getLottoTicket());
        return lottoMachine;
    }

    private WinningLotto requestWinningLotto() {
        List<LottoNumber> winningLottoNumbers = inputController.getWinningLottoNumbers();
        LottoNumber bonusLottoNumber = inputController.getBonusNumber();
        return new WinningLotto(winningLottoNumbers, bonusLottoNumber);
    }

    private void announceResult(LottoMachine lottoMachine, WinningLotto winningLotto) {
        LottoResult lottoResult = lottoMachine.getResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfitRate(lottoMachine.calculateProfit());
    }
}
