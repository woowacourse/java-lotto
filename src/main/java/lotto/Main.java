package lotto;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.MyLotto;
import lotto.domain.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        MyLotto myLotto = new MyLotto(getMyLotto(round));
        OutputView.printMyLotto(myLotto);

    }

    private static List<Lotto> getMyLotto(int round) {
        List<Lotto> myLotto = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            myLotto.add(new Lotto(NumberGenerator.create().getNumbers()));
        }

        return myLotto;
    }
}



