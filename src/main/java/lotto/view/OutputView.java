package lotto.view;

public class OutputView {

    private static final String OUTPUT_BUY_LOTTO_COUNTS = "%d개를 구매했습니다.\n";

    private OutputView() {
    }

    public static void outputBuyLottoCounts(final int lottoCounts) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNTS, lottoCounts);
    }
}
