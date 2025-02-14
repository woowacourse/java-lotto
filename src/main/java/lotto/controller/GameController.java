package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.util.ObjectCreator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    public void run() {
        LottoMoney lottoMoney = saveLottoMoney();
        LottoMachine lottoMachine = buyLottoTickets(lottoMoney);

        List<Lotto> lottoTickets = lottoMachine.getLottoTickets();
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = checkLottoResult(winningLotto, lottoTickets, lottoMoney);
        OutputView.writeLottoResult(lottoResult);
    }

    private LottoMoney saveLottoMoney() {
        LottoMoney lottoMoney = null;
        while (lottoMoney == null) {
            lottoMoney = ObjectCreator.useInputToCreateObject(() -> {
                String money = InputView.readLottoMoney();
                return new LottoMoney(money);
            });
        }
        return lottoMoney;
    }

    private LottoMachine buyLottoTickets(LottoMoney lottoMoney) {
        LottoMachine lottoMachine = null;
        while (lottoMachine == null) {
            lottoMachine = ObjectCreator.useInputToCreateObject(() -> new LottoMachine(lottoMoney));
        }
        return lottoMachine;
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();
        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        Lotto lotto = null;
        while (lotto == null) {
            lotto = ObjectCreator.useInputToCreateObject(() -> {
                String numbers = InputView.readWinningNumbers();
                return new Lotto(numbers);
            });
        }
        return lotto;
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        WinningLotto winningLotto = null;
        while (winningLotto == null) {
            winningLotto = ObjectCreator.useInputToCreateObject(() -> {
                String bonus = InputView.readBonusBall();
                return new WinningLotto(winningNumbers, bonus);
            });
        }
        return winningLotto;
    }

    private LottoResult checkLottoResult(WinningLotto winningLotto, List<Lotto> lottoTickets, LottoMoney lottoMoney) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);
        return lottoResult;
    }
}
