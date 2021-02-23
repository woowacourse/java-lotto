package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningLottoLine;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoTicket lottoTicket = createLottoTicket();
        OutputView.printLottoTicket(lottoTicket);

        WinningLottoLine winningLottoLine = new WinningLottoLine(createLottoLine(),
            createBonusLottoNumber());
        OutputView.printLottoResult(createLottoWinningResult(lottoTicket, winningLottoLine));
    }

    private LottoTicket createLottoTicket() {
        try {
            return new LottoTicket(InputView.getMoneyUserInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createLottoTicket();
        }
    }

    private LottoLine createLottoLine() {
        try {
            List<Integer> lottoNumbersUserInput = InputView.getLottoNumbersUserInput();
            List<LottoNumber> lottoNumbers = lottoNumbersUserInput.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
            return new LottoLine(lottoNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createLottoLine();
        }
    }

    private LottoNumber createBonusLottoNumber() {
        try {
            return new LottoNumber(InputView.getBonusLottoNumberUserInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createBonusLottoNumber();
        }
    }

    private Ranks createLottoWinningResult(LottoTicket lottoTicket,
        WinningLottoLine winningLottoLine) {
        return new Ranks(lottoTicket.checkLottoLines(winningLottoLine));
    }

}
