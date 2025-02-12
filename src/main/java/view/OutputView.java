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
}
