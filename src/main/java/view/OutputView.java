package view;

import dto.OutputLottosDto;
import java.util.List;

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
        System.out.println("거스름돈은 " + changeMoney + "원 입니다.");
    }
}
