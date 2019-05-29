package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {
        LottoBuyer person = makePerson();
        try {
            person.buyLotto();
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            System.exit(0);
        }
        OutputView.printContainingLottos(person);

        WinningLotto winningLotto = makeWinningLotto();

        WinningResult winningResult = person.checkWinningLotto(winningLotto);
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