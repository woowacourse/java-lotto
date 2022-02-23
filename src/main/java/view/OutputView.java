package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String lineSeparator = System.lineSeparator();

    public static void printPurchaseInfo(List<Lotto> lottos) {
        StringBuilder builder = new StringBuilder();

        builder.append(lottos.size())
                .append("개를 구매했습니다.")
                .append(lineSeparator);

        lottos.stream()
                .map(OutputView::formatLottoNumbers)
                .forEach(builder::append);

        print(builder.toString());
    }

    private static String formatLottoNumbers(Lotto lotto) {
        String lottoNumFormat = lotto.getChosenNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return "[" + lottoNumFormat + "]" + lineSeparator;
    }

    public static void printLottoResults(Map<LottoResult, Integer> lottoResults) {
        StringBuilder builder = new StringBuilder();

        builder.append(lineSeparator)
                .append("당첨 통계")
                .append(lineSeparator)
                .append("---------")
                .append(lineSeparator);

        Set<LottoResult> lottoResultKeys = lottoResults.keySet();

        lottoResultKeys.stream()
                .map(lottoResult -> formatLottoResult(lottoResult, lottoResults.get(lottoResult)))
                .forEach(builder::append);

        print(builder.toString());
    }

    private static String formatLottoResult(LottoResult lottoResult, int count) {
        StringBuilder builder = new StringBuilder();
        builder.append(lottoResult.getMatchCount())
                .append("개 일치");

        if (lottoResult.getHasBonus()) {
            builder.append(", 보너스 볼 일치");
        }

        builder.append(" (")
                .append(lottoResult.getPrize())
                .append("원) - ");

        builder.append(count).append("개")
                .append(lineSeparator);

        return builder.toString();
    }

    public static void printLottoResults(float profitRatio) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRatio);
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
