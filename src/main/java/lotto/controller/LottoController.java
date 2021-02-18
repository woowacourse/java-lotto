package lotto.controller;

import java.util.List;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = new Money(InputView.getMoney());
        LottoLine winLottoLine = new LottoLine(InputView.getLottoLine());
        LottoNumber bonusBallNumber = new LottoNumber(InputView.getBonusLottoNumber());
        LottoResult lottoResult = checkLottoTicket(LottoTicketFactory.createLottoTicket(money.getValue()), winLottoLine,
            bonusBallNumber);
        OutputView.printResult(lottoResult);
    }

    private LottoResult checkLottoTicket(LottoTicket lottoTicket, LottoLine winLottoLine,
        LottoNumber bonusBallNumber) {
        List<Rank> rankList = lottoTicket.matchLottoLines(winLottoLine.getValues(), bonusBallNumber);
        return new LottoResult(rankList);
    }

}
