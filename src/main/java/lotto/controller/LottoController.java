package lotto.controller;

import java.util.List;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = InputView.getMoney();
        LottoTicket lottoTicket = new LottoTicket(money.getValue());
        OutputView.printLottoTicket(lottoTicket);

        LottoLine winningLottoLine = InputView.getLottoLine();
        LottoNumber bonusLottoNumber = InputView.getBonusLottoNumber();
        List<Rank> ranks = lottoTicket.matchLottoLines(winningLottoLine.getValues(), bonusLottoNumber);
        OutputView.printResult(new LottoResult(ranks));
    }

}
