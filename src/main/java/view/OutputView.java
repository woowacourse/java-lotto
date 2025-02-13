package view;

import model.Lotto;
import model.LottoRepository;

public class OutputView {
    private static final String BUY_QUANTITY_PROMPT = "%d개를 구매했습니다.";
    private static final String WINNING_RATE_INFORMATION_UNDER_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String WINNING_RATE_INFORMATION_UPPER_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
    private static final String WINNING_RATE_INFORMATION_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 본전이라는 의미임)";
    public static void printRandomLotto(LottoRepository lottoRepository){
        for (Lotto lotto : lottoRepository.getLottos()) {
            System.out.println(lotto.printLotto());
        }
    }

    public static void printBuyQuantity(int quantity){
        System.out.println(String.format(BUY_QUANTITY_PROMPT, quantity));
    }

    public static void printResult(String result) {
        System.out.println(result);
    }

    public static void printWinningRate(double winningRate){
        if (winningRate == 1) {
            System.out.println(String.format(WINNING_RATE_INFORMATION_1, winningRate));
            return;
        }
        if (winningRate > 1) {
            System.out.println(String.format(WINNING_RATE_INFORMATION_UPPER_1, winningRate));
            return;
        }
        System.out.println(String.format(WINNING_RATE_INFORMATION_UNDER_1, winningRate));
    }

}
