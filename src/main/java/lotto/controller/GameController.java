package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.util.RandomNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private LottoMoney lottoMoney;

    public void run() {
        LottoMachine lottoMachine = buyLottoTickets();
        List<List<Integer>> lottoTickets = lottoMachine.getLottoTickets();
        OutputView.writeLottoTickets(lottoTickets);

        WinningLotto winningLotto = storeWinningLotto();

        LottoResult lottoResult = new LottoResult(winningLotto, lottoTickets);
        lottoResult.matchLottoTicketsResult();
        lottoResult.calculateLottoProfitRate(lottoMoney);

        OutputView.writeLottoResult(lottoResult);
    }

    private LottoMachine buyLottoTickets() {
        while(true) {
            String response = InputView.readLottoMoney();
            lottoMoney = new LottoMoney(response);
            RandomNumber randomNumber = new RandomNumber();
            return new LottoMachine(randomNumber, lottoMoney);
        }
    }

    private WinningLotto storeWinningLotto() {
        Lotto winningNumbers = storeWinningLottoNumbers();
        return storeWinningLottoBonus(winningNumbers);
    }

    private Lotto storeWinningLottoNumbers() {
        while(true) {
            String response = InputView.readWinningNumbers();
            return new Lotto(response);
        }
    }

    private WinningLotto storeWinningLottoBonus(Lotto winningNumbers) {
        while(true){
            String response = InputView.readBonusBall();
            return new WinningLotto(winningNumbers, response);
        }
    }
}
