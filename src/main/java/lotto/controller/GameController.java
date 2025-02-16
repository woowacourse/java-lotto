package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoMoneyService;
import lotto.service.LottoService;
import lotto.service.WinningLottoService;
import lotto.util.ObjectCreator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private final LottoService lottoService = new LottoService();
    private final LottoMoneyService lottoMoneyService = new LottoMoneyService();
    private final WinningLottoService winningLottoService = new WinningLottoService();

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
            lottoMoney = createLottoMoney();
        }
        return lottoMoney;
    }

    private LottoMoney createLottoMoney() {
        return ObjectCreator.useInputToCreateObject(() -> {
            String money = InputView.readLottoMoney();
            return lottoMoneyService.createLottoMoney(money);
        });
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
            lotto = createWinningLottoNumbers();
        }
        return lotto;
    }

    private Lotto createWinningLottoNumbers() {
        return ObjectCreator.useInputToCreateObject(() -> {
            String numbers = InputView.readWinningNumbers();
            return lottoService.createLotto(numbers);
        });
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        WinningLotto winningLotto = null;
        while (winningLotto == null) {
            winningLotto = createWinningLottoWithBonus(winningNumbers);
        }
        return winningLotto;
    }

    private WinningLotto createWinningLottoWithBonus(Lotto winningNumbers) {
        return ObjectCreator.useInputToCreateObject(() -> {
            String bonus = InputView.readBonusBall();
            return winningLottoService.createWinningLotto(winningNumbers, bonus);
        });
    }

    private LottoResult checkLottoResult(WinningLotto winningLotto, List<Lotto> lottoTickets, LottoMoney lottoMoney) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);
        return lottoResult;
    }
}
