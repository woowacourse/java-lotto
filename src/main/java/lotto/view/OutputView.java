package lotto.view;

import lotto.domain.Lottoes;
import lotto.domain.WinningLotto;

public class OutputView {
    public static void printLottoes(Lottoes lottoes) {
        System.out.println(lottoes.getLottoesSize()+"개를 구입했습니다.");
        System.out.println(lottoes.toString());
    }

    public static void printLotto(WinningLotto winningLotto) {
        System.out.println(winningLotto.toString());
    }
}
