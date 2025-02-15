package view;

import domain.enums.Prize;
import dto.OutputLottosDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public static void printBuyQuantity(final int buyQuantity) {
        System.out.println(buyQuantity + "개를 구매했습니다.");
    }

    public static void printLottos(List<OutputLottosDto> outputLottosDtos) {
        for (OutputLottosDto outputLottosDto : outputLottosDtos) {
            System.out.println(outputLottosDto.lottos().toString());
        }
    }

    public static void printChangeMoney(final int changeMoney) {
        if (changeMoney == 0) {
            return;
        }
        System.out.println("거스름돈은 " + changeMoney + "원 입니다.");
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률을 " + rateOfReturn + "입니다.");
    }

    public static void printPrizeResult(final Map<Prize, Integer> prizeResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (Map.Entry<Prize, Integer> entry : prizeResult.entrySet()) {
            printSinglePrizeResult(entry);
        }
    }

    private static void printSinglePrizeResult(Entry<Prize, Integer> entry) {
        if (entry.getKey().equals(Prize.MISS)) {
            return;
        }
        Prize currentPrize = entry.getKey();
        int prizeCount = entry.getValue();
        System.out.printf("%s (%d원)- %d개%n", currentPrize.getMatchedMessage(), currentPrize.getPrizeMoney(),
                prizeCount);
    }
}
