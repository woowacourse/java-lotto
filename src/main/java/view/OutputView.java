package view;

import domain.dto.BuyLottoResultDto;
import exception.LottoException;
import java.util.List;

public class OutputView {

    private final static String BUY_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private final static String BUY_LOTTO_AMOUNT_FORMAT = "%d개를 구매했습니다.\n";

    public static void printBuyLottoMoney() {
        printLine(BUY_LOTTO_MONEY);
    }

    private static void printLine(String message) {
        System.out.println(message);
    }

    public static void printError(LottoException lottoException) {
        printLine(lottoException.getMessage());
    }

    public static void printInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER);
    }

    public static void printInputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
    }

    public static void printBuyLottos(BuyLottoResultDto buyLottoResult) {
        System.out.println(String.format(BUY_LOTTO_AMOUNT_FORMAT, buyLottoResult.amount()));
        for (List<Integer> buyLotto : buyLottoResult.buyLottos()) {
            System.out.println(buyLotto);
        }
    }
}
