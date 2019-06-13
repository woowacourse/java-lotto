package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        int manualRound = InputView.inputManualRound(round);
        String[] number = InputView.inputManualNumbers(manualRound);
        UserLotto userLotto = new UserLotto(Parser.parseLotto(number), round - manualRound, new LottoNumberGenerator());
        OutputView.printMyLotto(userLotto, manualRound);

        WinningLotto winningLotto = new WinningLotto
                (Parser.parseWinningLotto(InputView.inputWinnerNumber())
                        , InputView.inputBonusBall());

        Winners winners = new Winners(winningLotto.makeRankResultList(userLotto));
        OutputView.printResultStatus(winners.getRankResult(), winners.calculateResultRate(round));
    }
}