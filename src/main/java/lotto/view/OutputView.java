package lotto.view;

import lotto.domain.Lotto;

public class OutputView {

    public static void printLottosSize(int lottosSize) {
        System.out.println(String.format("%d개를 구매했습니다.", lottosSize));
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d입니다.", earningRate));
    }

}
