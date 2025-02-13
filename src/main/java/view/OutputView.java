package view;

import dto.LottoDto;
import java.util.List;

public class OutputView {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_LOTTO_NUMBER_GUIDE_FORM = "%d개를 구매했습니다.\n";

    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printPurchaseLottos(List<LottoDto> lottosDto) {
        System.out.printf(PURCHASE_LOTTO_NUMBER_GUIDE_FORM, lottosDto.size());
        lottosDto.stream()
                .forEach(System.out::println);

    }

}
