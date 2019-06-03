package lotto;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MONEY_UNIT = 1000;
    private static final int PERCENT = 100;

    public static void main(String[] args) {
        int round = new Money(InputView.inputMoney()).getRound();
        int manualRound = InputView.inputHandNumber();
        String[] number = InputView.inputHandleNumber(manualRound);
        MyLotto myLotto = new MyLotto(getHandLottos(number));
        myLotto.addLottos(getMyLotto(round));
        OutputView.printMyLotto(myLotto, round);

        WinningLotto winningLotto = new WinningLotto(getWinningLotto(InputView.inputWinnerNumber()), InputView.inputBonusBall());
        List<Rank> ranks = getResult(myLotto, winningLotto);
        OutputView.printResultStatus(ranks, getReturnRate(ranks, round));
    }

    private static double getReturnRate(List<Rank> ranks, int inputMoney) {
        return calculateResultRate(inputMoney, getSum(ranks));
    }

    private static List<Lotto> getMyLotto(int round) {
        List<Lotto> myLotto = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            myLotto.add(new Lotto(NumberGenerator.create().getNumbers()));
        }

        return myLotto;
    }

    private static Lotto getWinningLotto(String numbers) {
        List<Number> lottos = new ArrayList<>();
        String[] winnerNumbers = numbers.split(",");

        for (String s : winnerNumbers) {
            lottos.add(new Number(Integer.parseInt(s)));
        }

        return new Lotto(lottos);
    }

    private static List<Rank> getResult(MyLotto myLotto, WinningLotto winningLotto) {
        List<Rank> rankResults = new ArrayList<>();

        for (int i = 0; i < myLotto.getSize(); i++) {
            rankResults.add(Rank.valueOf(winningLotto.getCount(myLotto.getIndexByLotto(i))
                    , winningLotto.matchBonus(myLotto.getIndexByLotto(i))));
        }

        return rankResults;
    }


    private static double getSum(List<Rank> ranks) {
        double sum = 0;

        for (Rank rank : Rank.values()) {
            sum += rank.getPrize(ranks);
        }

        if (sum == 0) {
            return 0;
        }
        return sum;
    }

    private static double calculateResultRate(int inputMoney, double sum) {
        return (sum / (inputMoney * MONEY_UNIT)) * PERCENT;
    }

    private static List<Lotto> getHandLottos(String[] handleNumbers) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < handleNumbers.length; i++) {
            String[] oneNumbers = handleNumbers[i].split(",");
            lottos.add(new Lotto(addLottoNumbers(i, oneNumbers)));
        }

        return lottos;
    }

    private static List<Number> addLottoNumbers(int i, String[] oneNumbers) {
        List<Number> numbers = new ArrayList<>();

        for (String oneNumber : oneNumbers) {
            numbers.add(new Number(Integer.parseInt(oneNumber)));
        }
        return numbers;
    }
}