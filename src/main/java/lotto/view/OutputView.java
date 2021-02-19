package lotto.view;

import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.RankComparator;

import java.util.Arrays;
import java.util.Collections;

public class OutputView {
    
    public void printPurchasingLotto(int purchasingLottoCount) {
        System.out.println(purchasingLottoCount + "개를 구매했습니다.");
    }
    
    public void printLottos(Lottos lottos) {
        lottos.toInts()
              .forEach(lotto -> {
                  Collections.sort(lotto);
                  System.out.println(lotto);
              });
        
        System.out.println();
    }
    
    public void printStatisticResult(LottoStatisticResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        
        Arrays.stream(Rank.values())
              .sorted(RankComparator.INSTANCE)
              .forEach(rank -> System.out.printf("%d개 일치 (%d원) - %d개%s", rank.getMatchCount(), rank.getReward(),
                      result.get(rank), System.lineSeparator()));
        
        System.out.printf("총 수익률은 %.2f입니다.", result.calculateIncomeRate());
    }
}
