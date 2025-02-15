package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    public void run() {
        LottoMoney lottoMoney = storeLottoMoney();
        LottoTickets lottoTickets = new LottoTickets();
        LottoMachine lottoMachine = new LottoMachine();
        for (Lotto lottoTicket : lottoMachine.generateLottoTickets(lottoMoney)) {
            lottoTickets.addLottoTicket(lottoTicket);
        }
        ;
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = new LottoResult();
        lottoResult.matchLottoTicketsResult(winningLotto, lottoTickets);
        lottoResult.calculateLottoProfitRate(lottoMoney);

        OutputView.writeLottoResult(lottoResult);
        OutputView.writeLottoResultProfitRate(lottoResult.calculateLottoProfitRate(lottoMoney));
    }

    private LottoMoney storeLottoMoney() {
        try {
            String response = InputView.readLottoMoney();
            return new LottoMoney(response);
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
