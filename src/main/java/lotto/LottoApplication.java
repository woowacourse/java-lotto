package lotto;

import lotto.domain.Budget;
import lotto.domain.LottoBuyer;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoBuyer person = makePerson();
        try {
            person.buyLotto();
            OutputView.printContainingLottos(person);
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
        }
    }

    private static LottoBuyer makePerson() {
        try {
            return new LottoBuyer(new Budget(InputView.inputBudget()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makePerson();
        }
    }
}
