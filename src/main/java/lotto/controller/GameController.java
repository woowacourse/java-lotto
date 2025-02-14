package lotto.controller;

import java.util.List;
import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private LottoMoney lottoMoney;

    public void run() {
        LottoMachine lottoMachine = buyLottoTickets();
        List<Set<Integer>> lottoTickets = lottoMachine.getLottoTickets();
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        lottoResult.calculateLottoProfitRate(lottoMoney);

        OutputView.writeLottoResult(lottoResult);
    }

    private LottoMachine buyLottoTickets() {
        try {
            String response = InputView.readLottoMoney();
            lottoMoney = new LottoMoney(response);
            LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
            return new LottoMachine(lottoNumberGenerator, lottoMoney);
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e.getMessage());
            return buyLottoTickets();
        }
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();
        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        try {
            String response = InputView.readWinningNumbers();
            return new Lotto(response);
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e.getMessage());
            return storeWinningLottoNumbers();
        }
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        try {
            String response = InputView.readBonusBall();
            return new WinningLotto(winningNumbers, response);
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e.getMessage());
            return storeWinningLottoBonus(winningNumbers);
        }
    }
}
