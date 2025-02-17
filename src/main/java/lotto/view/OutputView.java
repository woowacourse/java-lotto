package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Prize;

public class OutputView {
    public void printPurchasedLottos(Lottos lottos) {
        System.out.println(lottos.getQuantity() + "개를 구매했습니다.");
        for (List<Integer> lottoNumbers : lottos.getPurchasedLottos()) {
            System.out.println(lottoNumbers);
        }
        System.out.println();
    }

    public void printLottoResult(Map<Prize, Integer> prizeCount, double earningRate) {
        System.out.println("""
                
                당첨 통계
                ---------""");

        Prize.winningPlaces.forEach(prize -> printPrizeResult(prize, prizeCount.get(prize)));

        System.out.printf("총 수익률은 %.2f입니다.", Math.floor(earningRate * 100) / 100.0);
        System.out.println();
    }

    private void printPrizeResult(final Prize prize, final int count) {
        if (prize.isBonusMatch()) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개" + System.lineSeparator(),
                    prize.getMatchCount(), prize.getPrizeAmount(), count);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개" + System.lineSeparator(),
                prize.getMatchCount(), prize.getPrizeAmount(), count);
    }
}
