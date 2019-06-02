package lotto;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        MyLotto myLotto = new MyLotto(getMyLotto(round));
        OutputView.printMyLotto(myLotto);

        WinningLotto winningLotto = new WinningLotto(getWinningLotto(InputView.inputWinnerNumber()), InputView.inputBonusBall());

        System.out.println(winningLotto.toString());


    }

    private static List<Lotto> getMyLotto(int round) {
        List<Lotto> myLotto = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            myLotto.add(new Lotto(NumberGenerator.create().getNumbers()));
        }

        return myLotto;
    }

    private static Lotto getWinningLotto(String numbers) {
        List<Number> lotts = new ArrayList<>();
        String[] winnerNummber = numbers.split(",");

        for (String s : winnerNummber) {
            lotts.add(new Number(Integer.parseInt(s)));
        }

        return new Lotto(lotts);
    }
}



