package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
