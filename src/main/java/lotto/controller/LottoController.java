package lotto.controller;

import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoTicket lottoTicket = InputView.getLottoTicket();
        OutputView.printLottoTicket(lottoTicket);

        LottoLine winningLottoLine = InputView.getLottoLine();
        LottoNumber bonusLottoNumber = InputView.getBonusLottoNumber();
        Ranks ranks = new Ranks(lottoTicket.checkLottoLines(winningLottoLine, bonusLottoNumber));
        OutputView.printLottoResult(ranks);
    }

}
