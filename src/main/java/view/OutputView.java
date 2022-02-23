package view;

import domain.Lotto;
import domain.PurchasedLotto;

import java.util.stream.Collectors;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String DELIMITER = ", ";

    public static void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        System.out.println(purchasedLotto.getLottos().size() + PURCHASE_COUNT_MESSAGE);
        for (Lotto lotto : purchasedLotto.getLottos()) {
            System.out.print(LEFT_BRACKET);
            String lottoNumbers = String.join(DELIMITER, lotto.getLottoNumbers().stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                    .collect(Collectors.toList()));
            System.out.print(lottoNumbers);
            System.out.println(RIGHT_BRACKET);
        }
        System.out.print(System.lineSeparator());
    }
}
