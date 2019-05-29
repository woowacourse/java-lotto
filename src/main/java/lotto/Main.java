package lotto;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Money money = new Money(Integer.parseInt(InputView.inputMoney()));
        int numberOfLotto = money.getNumberOfLotto();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(new Lotto(RandomNumberGenerator.generate()));
        }
        OutputView.outputLotto(numberOfLotto, lottos);
    }
}
