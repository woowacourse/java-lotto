package view;

import domain.Lotto;
import java.util.List;
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
        String lottoNumFormat = lotto.getNumbers()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return "[" + lottoNumFormat + "]" + lineSeparator;
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
