package view;

import domain.Lottos;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class OutputView {
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void printPurchasedLottos(Lottos lottos) {
        System.out.println(lottos.getQuantity() + "개를 구매했습니다.");
        for (String lottoNumbers : lottos.getPurchasedLottos()) {
            System.out.println(lottoNumbers);
        }
        System.out.println();
    }
}
