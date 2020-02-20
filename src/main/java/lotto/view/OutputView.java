package lotto.view;

import lotto.domain.Lotto;

public class OutputView {

    public static void printLottosSize(int lottosSize) {
        System.out.println(String.format("%d개를 구매했습니다.", lottosSize));
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

}
