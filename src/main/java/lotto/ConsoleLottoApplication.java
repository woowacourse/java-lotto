package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            Money money = MoneyParser.parse(InputView.inputMoney());
            LottoCount lottoManualCount = LottoCountParser.parse(money, InputView.inputManualLottoCount());
            LottoTickets lottoTickets = LottoVendingMachine.purchase(money, createLottoGenerators(lottoManualCount));
            OutputView.printLottoTickets(lottoManualCount, lottoTickets);
            WinningLottoState winningLottoState = new WinningLottoState(createWinningLotto(), lottoTickets);
            OutputView.printWinningLottoState(winningLottoState, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private static LottoGenerators createLottoGenerators(LottoCount lottoManualCount) {
        try {
            return LottoGeneratorsParser.parse(InputView.inputManualLotto(lottoManualCount));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottoGenerators(lottoManualCount);
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return WinningLottoParser.parse(InputView.inputWinningLotto(), InputView.inputBonusNo());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }
}