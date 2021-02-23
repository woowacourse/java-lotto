package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.PayAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachineController {

    private final InputView inputView;

    private final OutputView outputView;

    public LottoMachineController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void purchase() {
        Lottos lottos = Lottos.from(getInputLottos());
        WinningLotto winningLotto = getInputWinningLotto();
        LottoStatisticResult result = lottos.match(winningLotto);
        outputView.printStatisticResult(result);
    }

    private List<Lotto> getInputLottos() {
        PayAmount payAmount = inputView.readPayAmount();
        LottoCount lottoCount = payAmount.calculateLottoCount();
        LottoCount manualLottoCount = inputView.readManualLottoCount();
        LottoCount autoLottoCount = lottoCount.subtract(manualLottoCount);

        outputView.printInputManualLottoNumbers();
        List<Lotto> manualLottos = getInputManualLottos(manualLottoCount);

        outputView.printPurchasingLotto(manualLottoCount.get(), autoLottoCount.get());
        List<Lotto> autoLottos = Lottos.from(autoLottoCount.get()).getLottos();
        outputView.printLottos(Lottos.from(autoLottos));
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        lottos.addAll(autoLottos);
        return lottos;
    }

    private List<Lotto> getInputManualLottos(LottoCount count) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < count.get(); i++) {
            List<Integer> lottoNumbers = inputView.readManualLottoNumbers();
            manualLottos.add(Lotto.fromNumbers(lottoNumbers));
        }
        return manualLottos;
    }

    private WinningLotto getInputWinningLotto() {
        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        return WinningLotto.of(lotto, bonusNumber);
    }
}
