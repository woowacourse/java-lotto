package lotto.view;

import java.util.List;
import lotto.domain.Lottos;

public class OutputView {

    public void printCount(int count) {
        System.out.println(String.format("%d개를 구매했습니다.", count));
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }
}
