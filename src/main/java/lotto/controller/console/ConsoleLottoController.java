package lotto.controller.console;

import lotto.domain.*;
import lotto.domain.vo.LottoResultVO;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutPutView;

import java.util.List;

public class ConsoleLottoController {

    public void run() {
        Price price = new Price(ConsoleInputView.InputPrice());
        NumberOfCustomLotto customAmount = new NumberOfCustomLotto(ConsoleInputView.InputNumberOfCustomLotto(), price);
        List<String> manualNumber = ConsoleInputView.InputCustomLottoNumber(customAmount);

        LottoTicket lottoTicket = new LottoTicket(customAmount, manualNumber);
        ConsoleOutPutView.showLottoTicket(lottoTicket);

        WinningLotto winningLotto = new WinningLotto(ConsoleInputView.InputWinLottoNumber(), ConsoleInputView.InputBonusNumber());
        LottoResult result = new LottoResult(lottoTicket, winningLotto);
        LottoResultVO result_vo = new LottoResultVO(result.matchLotto(), price);
        ConsoleOutPutView.showLottoResult(result_vo);
    }

}
