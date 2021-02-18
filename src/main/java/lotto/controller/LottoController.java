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
        try {
            LottoTicket lottoTicket = getLottoTicket();
            OutputView.printLottoTicket(lottoTicket);
            LottoResult lottoResult = checkLottoTicket(lottoTicket, new LottoLine(InputView.getLottoLine()),
                    new LottoNumber(InputView.getBonusLottoNumber()));
            OutputView.printResult(lottoResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private LottoTicket getLottoTicket() {
        Money money = new Money(InputView.getMoney());
        return LottoTicketFactory.createLottoTicket(money.getValue());
    }

    private LottoResult checkLottoTicket(LottoTicket lottoTicket, LottoLine winLottoLine,
                                         LottoNumber bonusBallNumber) {
        List<Rank> rankList = lottoTicket
                .matchLottoLines(winLottoLine.getValues(), bonusBallNumber);
        return new LottoResult(rankList);
    }

}
