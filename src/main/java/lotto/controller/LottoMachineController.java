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
        PayAmount payAmount = inputView.readPayAmount();
        LottoCount lottoCount = payAmount.getLottoCount();
        LottoCount manualLottoCount = inputView.readManualLottoCount();
        LottoCount autoLottoCount = lottoCount.subtract(manualLottoCount);

        outputView.printInputManualLottoNumbers();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount.get(); i++) {
            List<Integer> lottoNumbers = inputView.readManualLottoNumbers();
            manualLottos.add(Lotto.fromNumbers(lottoNumbers));
        }


        outputView.printPurchasingLotto(autoLottoCount.get());

        Lottos lottos = Lottos.from(autoLottoCount.get());
        outputView.printLottos(lottos);

        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();

        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);
        LottoStatisticResult result = Rank.match(lottos, winningLotto);

        outputView.printStatisticResult(result);
    }
}
