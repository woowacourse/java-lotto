package view;

import domain.Lottos;
import domain.Prize;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    public void printPurchasedLottos(Lottos lottos) {
        System.out.println(lottos.getQuantity() + "개를 구매했습니다.");
        for (String lottoNumbers : lottos.getPurchasedLottos()) {
            System.out.println(lottoNumbers);
        }
        System.out.println();
    }

    public void printLottoResult(List<Prize> prizes, double earningRate) {
        System.out.println("""
                
                당첨 통계
                ---------""");
        final List<Integer> numberOfPizes = Arrays.stream(Prize.values())
                .map(p -> Collections.frequency(prizes, p))
                .toList();

        for (int i = Prize.values().length - 2; i >= 0; i--) {
            printPrizeResult(i, numberOfPizes);
        }

        double formattedEarningRate = (long) (earningRate * 100) / 100.0;

        System.out.printf("총 수익률은 %.2f입니다.", formattedEarningRate);
        System.out.println();
    }

    private static void printPrizeResult(final int order, final List<Integer> numberOfPizes) {
        Prize prize = Prize.values()[order];
        if (prize.isBonusMatch()) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개" + System.lineSeparator(),
                    prize.getMatchCount(), prize.getPrizeAmount(), numberOfPizes.get(order));
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개" + System.lineSeparator(),
                prize.getMatchCount(), prize.getPrizeAmount(), numberOfPizes.get(order));
    }
}
