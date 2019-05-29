package lotto;

import lotto.domain.Budget;
import lotto.domain.Person;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        Person peson = makePerson();
    }

    private static Person makePerson() {
        try {
            return new Person(new Budget(InputView.inputBudget()));
        } catch (Exception e) {
            OutputView.printErrorMsg(e);
            return makePerson();
        }
    }
}
