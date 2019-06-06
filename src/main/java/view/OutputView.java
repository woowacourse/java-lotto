package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public static void showIssuedLottos(List<Lotto> autoIssuedLottos) {
        for (Lotto lotto : autoIssuedLottos) {
            System.out.println(lotto);
       }
    }
}
