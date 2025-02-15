package view;

import java.util.Map;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoRepository;
import model.RankType;

public class OutputView {
    private static final String BUY_QUANTITY_PROMPT = "%d개를 구매했습니다.";
    private static final String WINNING_RATE_INFORMATION_UNDER_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String WINNING_RATE_INFORMATION_UPPER_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
    private static final String WINNING_RATE_INFORMATION_1 = "총 수익률 %.2f입니다. (기준이 1이기 때문에 결과적으로 본전이라는 의미임)";
    private static final String LOTTO_RESULT_PRINT_FORMAT = "%d개 일치, (%d원)- %d개\n";
    private static final String LOTTO_RESULT_BONUS_BALL_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    public static void printRandomLotto(LottoRepository lottoRepository) {
        for (Lotto lotto : lottoRepository.getLottos()) {
            System.out.println(printLotto(lotto));
        }
    }

    private static String printLotto(Lotto lotto) {
        return "[" + String.join(", ",
                lotto.getRandomNumbers().stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        ) + "]";
    }

    public static void printBuyQuantity(int quantity) {
        System.out.println(String.format(BUY_QUANTITY_PROMPT, quantity));
    }

    public static void printWinningRate(double winningRate) {
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

    public static void printResult(Map<RankType, Integer> rankTypeMap) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(makeLottoResult(rankTypeMap));
    }

    public static String makeLottoResult(Map<RankType, Integer> map){
        StringBuilder stringBuilder = new StringBuilder();

        for(RankType rankType : map.keySet()){
            if(rankType == RankType.SECOND){
                stringBuilder.append(String.format(LOTTO_RESULT_BONUS_BALL_PRINT_FORMAT, rankType.getWinningCount(), rankType.getPrice(), map.get(rankType)));
                continue;
            }
            stringBuilder.append(String.format(LOTTO_RESULT_PRINT_FORMAT, rankType.getWinningCount(), rankType.getPrice(), map.get(rankType)));
        }

        return stringBuilder.toString();
    }

}
