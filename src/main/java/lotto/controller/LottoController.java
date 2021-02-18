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

import static lotto.view.ErrorMessages.ERROR_BONUS_LOTTO_NUMBER_DUPLICATED;

public class LottoController {

    public void run() {
        try {
            LottoTicket lottoTicket = getLottoTicket();

            OutputView.printLottoTicket(lottoTicket);
            OutputView.printResult(getLottoResult(lottoTicket));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private LottoTicket getLottoTicket() {
        Money money = new Money(InputView.getMoney());
        return LottoTicketFactory.createLottoTicket(money.getValue());
    }

    private LottoResult getLottoResult(LottoTicket lottoTicket) {
        LottoLine lottoLine = new LottoLine(InputView.getLottoLine());
        LottoNumber bonusLottoNumber = new LottoNumber(InputView.getBonusLottoNumber());

        if (lottoLine.containNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_LOTTO_NUMBER_DUPLICATED.getMessage());
        }

        return checkLottoTicket(lottoTicket, lottoLine, bonusLottoNumber);
    }

    private LottoResult checkLottoTicket(LottoTicket lottoTicket, LottoLine winLottoLine,
                                         LottoNumber bonusBallNumber) {
        List<Rank> rankList = lottoTicket
                .matchLottoLines(winLottoLine.getValues(), bonusBallNumber);
        return new LottoResult(rankList);
    }
}
