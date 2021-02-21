package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoTicket lottoTicket = createLottoTicket();
        OutputView.printLottoTicket(lottoTicket);

        LottoLine winningLottoLine = createWinningLottoLine();
        LottoNumber bonusLottoNumber = createBonusLottoNumber();
        OutputView.printLottoResult(createLottoWinningResult(lottoTicket, winningLottoLine, bonusLottoNumber));
    }

    private LottoTicket createLottoTicket(){
        try {
            return new LottoTicket(InputView.getMoneyUserInput());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return createLottoTicket();
        }
    }

    private LottoLine createWinningLottoLine(){
        try {
            List<Integer> lottoNumbersUserInput = InputView.getLottoNumbersUserInput();
            List<LottoNumber> lottoNumbers = lottoNumbersUserInput.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
            return new LottoLine(lottoNumbers);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return createWinningLottoLine();
        }
    }

    private LottoNumber createBonusLottoNumber(){
        try {
            return new LottoNumber(InputView.getBonusLottoNumberUserInput());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return createBonusLottoNumber();
        }
    }

    private Ranks createLottoWinningResult(LottoTicket lottoTicket, LottoLine winningLottoLine, LottoNumber bonusLottoNumber){
        return new Ranks(lottoTicket.checkLottoLines(winningLottoLine, bonusLottoNumber));
    }

}
