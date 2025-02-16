package lotto.view;

import java.util.Map;
import java.util.StringJoiner;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.Lottos;
import lotto.domain.Prizes;
import lotto.domain.Rank;

public class OutputView {

    private static final String RESULT_HEADER = "당첨 통계";
    private static final String RESULT_MULTI_DASH = "---------";
    private static final String COUNT_UNIT = "개";
    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.2f입니다.";

    public void printCount(final int count) {
        System.out.println(String.format(PURCHASE_MESSAGE, count));
    }

    public void printLottos(Lottos lottos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos.getLottos()) {
            LottoNumbers lottoNumbers = lotto.getLottoNumbers();
            StringJoiner joiner = new StringJoiner(", ");
            buildEachLotto(joiner, lottoNumbers);
            stringBuilder.append("[")
                    .append(joiner)
                    .append("]")
                    .append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private void buildEachLotto(StringJoiner joiner, LottoNumbers lottoNumbers) {
        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumbers()) {
            joiner.add(String.valueOf(lottoNumber.getNumber()));
        }
    }

    public void printResult(Prizes prizes) {
        System.out.println(System.lineSeparator() + RESULT_HEADER);
        System.out.println(RESULT_MULTI_DASH);

        StringBuilder stringBuilder = new StringBuilder();

        Map<Rank, Integer> results = prizes.getResults();
        for (Rank rank : Rank.getValidRank()) {
            stringBuilder.append(rank.getMessage())
                    .append(results.get(rank))
                    .append(COUNT_UNIT)
                    .append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_MESSAGE, profitRate));
    }

}
