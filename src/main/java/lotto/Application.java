package lotto;

import lotto.domain.count.Count;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.domain.result.LottoResult;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;

public class Application {

    private static LottoService lottoService = new LottoService();

    public static void main(String[] args) {
        LottoMoney lottoMoney = createLottoMoney();
        Count count = createCount(lottoMoney);
        LottoTickets lottoTickets = createLottoTickets(count);

        OutputView.printLottoTickets(lottoTickets);

        WinningLotto winningLotto = createWinningLotto();
        LottoResult lottoResult = createLottoResult(lottoTickets, winningLotto);

        OutputView.printLottoResult(lottoResult);
        OutputView.printProfit(lottoResult.getProfit(lottoMoney));
    }

    private static LottoMoney createLottoMoney() {
        String input = InputView.inputLottoMoney();
        return lottoService.createLottoMoney(input);
    }

    private static Count createCount(LottoMoney lottoMoney) {
        String manualCountInput = InputView.inputManualCounts();
        return lottoService.createCount(lottoMoney, manualCountInput);
    }

    private static LottoTickets createLottoTickets(Count count) {
        List<String> lottoTicketDtoInputs = InputView.inputManualNumber(count.getManualCounts());
        return lottoService.createLottoTickets(lottoTicketDtoInputs, count);
    }

    private static WinningLotto createWinningLotto() {
        String inputNumbers = InputView.inputWinningLottoNumber();
        String bonusNumber = InputView.inputBonusNumber();
        return lottoService.createWinningLotto(inputNumbers, bonusNumber);

    }

    private static LottoResult createLottoResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        return lottoService.createLottoResult(lottoTickets, winningLotto);
    }
}
