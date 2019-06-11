package lotto.view;

import lotto.domain.*;

public class OutputView {
    private static final String REQUEST_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";

    public static void requestManualLottosMessage() {
        System.out.println(REQUEST_MANUAL_LOTTO);
    }

    public static void requestWinningNumbersMessage() {
        System.out.println(REQUEST_WINNING_NUMBERS);
    }

    public static void outputLottosPurchaseMessage(PurchaseInformation purchaseInformation) {
        System.out.println(OutputViewFactory.outputLottosPurchaseMessage(purchaseInformation));
    }

    public static void outputLottos(Lottos lottos) {
        for (String lotto : OutputViewFactory.outputLottos(lottos)) {
            System.out.println(lotto);
        }
    }

    public static void outputResult(LottoResult lottoResult) {
        System.out.println(STATISTICS_MESSAGE);
        for (String result : OutputViewFactory.outputResult(lottoResult)) {
            System.out.println(result);
        }
        System.out.println(OutputViewFactory.outputYield(lottoResult));
    }
}
