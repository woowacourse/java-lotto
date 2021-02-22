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
        String[] splitLottoNumbersInput = InputView.getSplitLottoNumbers();
        LottoLine lottoLine = getLottoLine(splitLottoNumbersInput);

        String bonusBallInput = InputView.getBonusLottoNumber();
        LottoNumber bonusLottoNumber = new LottoNumber(bonusBallInput);

        return new WinningNumbers(lottoLine, bonusLottoNumber);
    }

    private LottoLine getLottoLine(String[] splitLottoNumbersInput) {
        ArrayList<LottoNumber> lottoNumberList = new ArrayList();

        for (int i = 0; i < splitLottoNumbersInput.length; i++) {
            lottoNumberList.add(new LottoNumber(splitLottoNumbersInput[i]));
        }

        return new LottoLine(lottoNumberList);
    }

    private LottoResult checkLottoTicket(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        List<Rank> rankList = lottoTicket
                .matchLottoLines(winningNumbers);
        return new LottoResult(rankList);
    }
}
