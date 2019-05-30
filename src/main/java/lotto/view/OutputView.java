package lotto.view;

import lotto.domain.Lottoes;
import lotto.domain.Rank;
import lotto.domain.Statistics;
import lotto.domain.WinningLotto;

import java.util.Map;

public class OutputView {
    public static void printLottoes(Lottoes lottoes) {
        System.out.println(lottoes.getLottoesSize()+"개를 구입했습니다.");
        System.out.println(lottoes.toString());
    }

    public static void printStatistics(Statistics statistics) {
        Map<Rank,Integer> map = statistics.getResult();
        System.out.println("당첨통계");
        System.out.println("------------");
        for(Rank rank : Rank.values()){
            System.out.println(rank.getMatchCount()+"개 일치("+rank.getMoney()+")원-"+map.get(rank)+"개");
        }
    }
}
