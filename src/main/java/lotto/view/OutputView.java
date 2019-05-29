package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    public static void outputLotto(int numberOfLotto, List<Lotto> lottos) {
        System.out.println(numberOfLotto + "개를 구매했습니다.");
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append("[");
            stringBuilder.append(String.join(",", lotto.convertStringList()));
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
