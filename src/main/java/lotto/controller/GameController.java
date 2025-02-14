package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private LottoMoney lottoMoney;

    public void run() {
        LottoMachine lottoMachine = buyLottoTickets();

        List<List<Integer>> lottoTickets = lottoMachine.getLottoTickets();
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = checkLottoResult(winningLotto, lottoTickets);
        OutputView.writeLottoResult(lottoResult);
    }

    private LottoMachine buyLottoTickets() {
        while (true) {
            try {
                String money = InputView.readLottoMoney();
                lottoMoney = new LottoMoney(money);
                return new LottoMachine(lottoMoney);
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e);
            }
        }
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();
        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        while (true) {
            try {
                String numbers = InputView.readWinningNumbers();
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e);
            }
        }
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        while (true) {
            try {
                String bonus = InputView.readBonusBall();
                return new WinningLotto(winningNumbers, bonus);
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e);
            }
        }
    }

    private LottoResult checkLottoResult(WinningLotto winningLotto, List<List<Integer>> lottoTickets) {
        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);

        lottoResult.matchLottoTicketsResult();
        lottoResult.calculateLottoProfitRate(lottoMoney);

        return lottoResult;
    }
}
