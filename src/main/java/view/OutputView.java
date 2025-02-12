package view;

import domain.Lotto;
import domain.LottoStats;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printLottoStats(LottoStats lottoStats) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(lottoStats);
    }

    public static void printEarningRate(LottoStats lottoStats, int price){
        System.out.printf("총 수익률은 %.2f입니다.", 1. * lottoStats.getTotalPrize() / price);
    }
}
