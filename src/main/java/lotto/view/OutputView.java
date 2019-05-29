package lotto.view;

import com.google.common.base.Joiner;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

import java.util.List;

public class OutputView {
    private static final String START_BRACE = "[";
    private static final String END_BRACE = "]";
    private static final String JOINER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------\n";

    public static void outputLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개 구매했습니다.");
        StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lottos.getLottos()) {
            builder.append(makeNumbersView(lotto.getLottoNumbers()));
        }
        System.out.println(builder);
    }

    private static StringBuilder makeNumbersView(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder(START_BRACE);
        builder.append(Joiner.on(JOINER).join(numbers));
        builder.append(END_BRACE);
        builder.append(NEW_LINE);
        return builder;
    }

    public static void outputResult(LottoResult lottoResult) {
        StringBuilder builder = new StringBuilder();
        builder.append(STATISTICS_MESSAGE);
        builder.append(lottoResult.getResultMessage());
        builder.append("총 수익률은 ");
        builder.append(lottoResult.calculateYield() * 100);
        builder.append("%입니다.");
        System.out.println(builder);
    }
}
