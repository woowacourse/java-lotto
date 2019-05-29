package lotto.view;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class OutputView {
    private static final String OUTPUT_BUY_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d장을 구매하셨습니다.";
    public static void outputUserBuyLottoCount(long manualCount, long autoCount) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNT, manualCount, autoCount);
    }
}
