package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {
        LottoBuyer buyer = makePerson();
        try {
            buyer.buyLotto(makeLottoManually(buyer));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            System.exit(0);
        }
        OutputView.printContainingLottos(buyer);

        WinningLotto winningLotto = makeWinningLotto();

        WinningResult winningResult = buyer.checkWinningLotto(winningLotto);
        OutputView.printResult(winningResult);
    }

    private static LottoBuyer makePerson() {
        try {
            return new LottoBuyer(new Budget(InputView.inputBudget()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makePerson();
        }
    }

    private static List<Lotto> makeLottoManually(LottoBuyer buyer) {
        try {
            int countOfManualLotto = InputView.inputCountOfManualLotto();
            validatePositive(countOfManualLotto);
            buyer.validateAffordability(countOfManualLotto);
            return InputView.inputManualLottos(countOfManualLotto);
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeLottoManually(buyer);
        }
    }

    private static void validatePositive(int countOfManualLotto) {
        if (countOfManualLotto < 0) {
            throw new InvalidNumberException("음수를 입력할 수 없습니다.");
        }
    }

    private static WinningLotto makeWinningLotto() {
        try {
            Lotto winningLottoNo = Lotto.of(InputView.inputWinningLotto().stream().map(LottoNo::new).collect(Collectors.toList()));
            return WinningLotto.of(winningLottoNo, new LottoNo(InputView.inputBonusNo()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeWinningLotto();
        }
    }
}