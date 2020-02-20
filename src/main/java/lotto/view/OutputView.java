package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    public static void printLottosSize(int lottosSize) {
        System.out.println(String.format("%d개를 구매했습니다.",lottosSize));
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public static void printStatistic(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber,
        int fifthNumber) {

        System.out.println(Rank.print(firstNumber));

    }
}
