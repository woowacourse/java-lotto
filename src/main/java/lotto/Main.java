package lotto;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        int manualRound = InputView.inputHandNumber();
        String[] number = InputView.inputHandleNumbers(manualRound);
        Lottoes lottoes = new Lottoes(getHandLottos(number), round);
        OutputView.printMyLotto(lottoes, round);

        WinningLotto winningLotto = new WinningLotto
                (parseWinningLotto(InputView.inputWinnerNumber())
                        , InputView.inputBonusBall());

        Winners winners = Winners.create(lottoes, winningLotto);
        OutputView.printResultStatus(winners.getRankResult(), winners.calculateResultRate(round));
    }

    private static Lotto parseWinningLotto(String numbers) {
        List<Number> lottos = new ArrayList<>();
        String[] winnerNumbers = numbers.split(DELIMITER);

        for (String s : winnerNumbers) {
            lottos.add(new Number(Integer.parseInt(s)));
        }

        return new Lotto(lottos);
    }

    private static List<Lotto> getHandLottos(String[] handleNumbers) {
        List<Lotto> lottos = new ArrayList<>();

        for (String handleNumber : handleNumbers) {
            String[] oneNumbers = handleNumber.split(DELIMITER);
            lottos.add(new Lotto(addLottoNumbers(oneNumbers)));
        }

        return lottos;
    }

    private static List<Number> addLottoNumbers(String[] oneNumbers) {
        List<Number> numbers = new ArrayList<>();

        for (String oneNumber : oneNumbers) {
            numbers.add(new Number(Integer.parseInt(oneNumber)));
        }
        return numbers;
    }


}