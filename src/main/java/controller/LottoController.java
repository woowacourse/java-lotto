package controller;

import domain.Lotto;
import domain.WinningLotto;
import domain.WinningResult;
import domain.vo.Money;
import service.LottoManager;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoManager lottoManager;

    public LottoController(InputView inputView, OutputView outputView, LottoManager lottoManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoManager = lottoManager;
    }

    public void run() {
        Money money = inputView.inputMoney();
        final int lottoCount = money.getLottoCount();
        outputView.printLottoCount(lottoCount);

        List<Lotto> lottos = lottoManager.generateLottos(lottoCount);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = inputView.inputWinningLotto();
        WinningResult winningResult = lottoManager.getWinningResult(lottos, winningLotto);
        outputView.printWinningResult(winningResult);
        outputView.printRevenue(lottoManager.getRevenue(winningResult, money));
    }
}
