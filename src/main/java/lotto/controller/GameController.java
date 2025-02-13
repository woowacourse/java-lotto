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
                String response = InputView.readLottoMoney();
                lottoMoney = new LottoMoney(response);
                return new LottoMachine(lottoMoney);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
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
                String response = InputView.readWinningNumbers();
                return new Lotto(response);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        while (true) {
            try {
                String response = InputView.readBonusBall();
                return new WinningLotto(winningNumbers, response);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
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
