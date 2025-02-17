package lotto.controller;

import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.util.LottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private LottoMoney lottoMoney;
    private LottoTickets lottoTickets;

    public void run() {
        buyLottoTickets();
        findLottoTicketsResult();
    }

    private void buyLottoTickets() {
        lottoMoney = storeLottoMoney();
        lottoTickets = new LottoTickets();
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(lottoNumberGenerator);
        for (Lotto lottoTicket : lottoMachine.generateLottoTickets(lottoMoney)) {
            lottoTickets.addLottoTicket(lottoTicket);
        };

        OutputView.writeLottoTickets(lottoTickets);
    }

    private void findLottoTicketsResult() {
        WinningLotto winningLotto = storeWinningLotto();
        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);

        OutputView.writeLottoResult(lottoResult);
        OutputView.writeLottoResultProfitRate(lottoResult.calculateLottoProfitRate(lottoMoney));
    }

    private LottoMoney storeLottoMoney() {
        try {
            int lottoMoney = InputView.readLottoMoney();
            return new LottoMoney(lottoMoney);
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e.getMessage());
            return storeLottoMoney();
        }
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();
        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        try {
            Set<Integer> lottoNumbers = InputView.readWinningNumbers();
            return new Lotto(lottoNumbers);
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
