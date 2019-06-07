package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        int manualRound = InputView.inputHandNumber();
        String[] number = InputView.inputHandleNumber(manualRound);
        MyLotto myLotto = MyLotto.create(number, round);
        OutputView.printMyLotto(myLotto, round);

        WinningLotto winningLotto = WinningLotto.create(InputView.inputWinnerNumber(), InputView.inputBonusBall());
        Winners winners = Winners.create(myLotto, winningLotto);
        OutputView.printResultStatus(winners.getRankResult(), winners.calculateResultRate(round));
    }
}