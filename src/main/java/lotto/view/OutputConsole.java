package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputConsole {

    public static void outputLotto(int numberOfLotto, Lottos lottos) {
        System.out.println(numberOfLotto + "개를 구매했습니다.");
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos.getList()) {
            stringBuilder.append("[");
            stringBuilder.append(String.join(",", lotto.convertStringList()));
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
