package lotto.view;

import com.google.common.base.Joiner;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.List;

public class OutputView {
    private static final String START_BRACE = "[";
    private static final String END_BRACE = "]";
    private static final String JOINER = ", ";

    public static void outputLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(makeNumbersView(lotto.getLottoNumbers()));
        }
    }

    private static StringBuilder makeNumbersView(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder(START_BRACE);
        builder.append(Joiner.on(JOINER).join(numbers));
        builder.append(END_BRACE);
        return builder;
    }
}
