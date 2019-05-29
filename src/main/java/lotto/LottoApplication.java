package lotto;

import lotto.domain.Budget;
import lotto.domain.Lotto;
import lotto.domain.LottoBuyer;
import lotto.domain.LottoNo;
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

        Lotto WinningLotto = makeWinningLotto();
    }

    private static LottoBuyer makePerson() {
        try {
            return new LottoBuyer(new Budget(InputView.inputBudget()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makePerson();
        }
    }

    private static Lotto makeWinningLotto() {
        try {
            return Lotto.of(InputView.inputWinningLotto().stream().map(LottoNo::new).collect(Collectors.toList()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makeWinningLotto();
        }
    }
}
