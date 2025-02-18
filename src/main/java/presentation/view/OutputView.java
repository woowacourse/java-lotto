package presentation.view;

import domain.enums.Prize;
import dto.OutputPurchasedLottosDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public static void printBuyQuantity(final int buyQuantity) {
        System.out.println(buyQuantity + "개를 구매했습니다.");
    }

    public static void printLottos(List<OutputPurchasedLottosDto> outputPurchasedLottosDtos) {
        for (OutputPurchasedLottosDto outputPurchasedLottosDto : outputPurchasedLottosDtos) {
            System.out.println(outputPurchasedLottosDto.lottos().toString());
        }
    }

    public static void printChangeMoney(final int changeMoney) {
        if (changeMoney == 0) {
            return;
        }
        System.out.println("거스름돈은 " + changeMoney + "원 입니다.");
    }

    public static void printPrizeResult(final Map<Prize, Integer> prizeResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (Map.Entry<Prize, Integer> entry : prizeResult.entrySet()) {
            printSinglePrizeResult(entry);
        }
    }

    private static void printSinglePrizeResult(Entry<Prize, Integer> entry) {
        if (entry.getKey().equals(Prize.EMPTY)) {
            return;
        }
        Prize currentPrize = entry.getKey();
        int prizeCount = entry.getValue();
        System.out.printf("%s (%d원)- %d개%n", currentPrize.getMatchedMessage(), currentPrize.getPrizeMoney(),
                prizeCount);
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
