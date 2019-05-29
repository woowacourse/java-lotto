package lotto.view;

import lotto.domain.Lottoes;

public class OutputView {
    public static void printLottoes(Lottoes lottoes) {
        System.out.println(lottoes.getLottoesSize()+"개를 구입했습니다.");
        System.out.println(lottoes.toString());
    }
}
