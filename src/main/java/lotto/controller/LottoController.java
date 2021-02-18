package lotto.controller;

import java.util.List;
import lotto.domain.lotto.utils.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoTicket lottoTicket = new LottoTicket(InputView.getMoney());
        OutputView.printLottoTicket(lottoTicket);

        LottoLine winningLottoLine = InputView.getLottoLine();
        LottoNumber bonusLottoNumber = InputView.getBonusLottoNumber();
        List<Rank> ranks = lottoTicket.matchLottoLines(winningLottoLine.getValues(), bonusLottoNumber);
        OutputView.printResult(new LottoResult(ranks));
    }

}
