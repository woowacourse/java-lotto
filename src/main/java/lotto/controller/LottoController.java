package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        try {
            LottoTicket lottoTicket = getLottoTicket();
            OutputView.printLottoTicket(lottoTicket);
            WinningNumbers winningNumbers = getWinningNumbers();
            OutputView.printResult(checkLottoTicket(lottoTicket, winningNumbers));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private LottoTicket getLottoTicket() {
        Money money = new Money(InputView.getMoney());
        return LottoTicketGenerator.getInstance().createLottoTicket(money.getValue());
    }

    private WinningNumbers getWinningNumbers() {
        LottoLine lottoLine = new LottoLine(getLottoLine(InputView.getLottoLine()));
        LottoNumber bonusLottoNumber = new LottoNumber(InputView.getBonusLottoNumber());
        WinningNumbers winningNumbers = new WinningNumbers(lottoLine, bonusLottoNumber);
        return winningNumbers;
    }

    private List<LottoNumber> getLottoLine(String[] splitLottoNumbersInput) {
        ArrayList<LottoNumber> lottoNumberList = new ArrayList();

        for (int i = 0; i < splitLottoNumbersInput.length; i++) {
            lottoNumberList.add(new LottoNumber(splitLottoNumbersInput[i]));
        }

        return lottoNumberList;
    }

    private LottoResult checkLottoTicket(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        List<Rank> rankList = lottoTicket
                .matchLottoLines(winningNumbers);
        return new LottoResult(rankList);
    }
}
