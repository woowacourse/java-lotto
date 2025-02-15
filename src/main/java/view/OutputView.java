package view;

import static constant.WinningCount.FIVE;
import static constant.WinningCount.FIVE_BONUS;
import static constant.WinningCount.FOUR;
import static constant.WinningCount.SIX;
import static constant.WinningCount.THREE;

import constant.WinningCount;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printLottoReceipt(IssuedLottosDto lottosDto) {
        List<IssuedLottoDto> lottos = lottosDto.lottos();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto ->
                printLotto(getSortedNumbers(lotto.numbers()))
        );
    }

    private static List<Integer> getSortedNumbers(List<Integer> numbers) {
        return new ArrayList<>(numbers).stream().sorted().collect(Collectors.toList());
    }

    private static void printLotto(List<Integer> lotto) {
        System.out.println(
                lotto.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"))
        );
    }

    public static void printLottoResult(Map<WinningCount, Integer> result, double earningRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("%d개 일치(%,d원)- %d개\n", THREE.getMatchedCount(), THREE.getAmount(),
                result.getOrDefault(THREE, 0));
        System.out.printf("%d개 일치(%,d원)- %d개\n", FOUR.getMatchedCount(), FOUR.getAmount(),
                result.getOrDefault(FOUR, 0));
        System.out.printf("%d개 일치(%,d원)- %d개\n", FIVE.getMatchedCount(), FIVE.getAmount(),
                result.getOrDefault(FIVE, 0));
        System.out.printf("%d개 일치, 보너스 볼 일치(%,d원)- %d개\n", FIVE_BONUS.getMatchedCount(), FIVE_BONUS.getAmount(),
                result.getOrDefault(FIVE_BONUS, 0));
        System.out.printf("%d개 일치(%,d원)- %d개\n", SIX.getMatchedCount(), SIX.getAmount(), result.getOrDefault(SIX, 0));
        System.out.printf("총 수익률은 %.2f입니다.(%s)", earningRate, printEarningRate(earningRate));
    }

    private static String printEarningRate(double earningRate) {
        if (earningRate > 1) {
            return "이득";
        }
        return "손해";
    }
}
