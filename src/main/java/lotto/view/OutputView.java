package lotto.view;

import java.util.List;

public class OutputView {

    public void printCount(int count) {
        System.out.println(String.format("%d개를 구매했습니다.", count));
    }

    public void printLottos(List<List<Integer>> lottos) {
        for (int i = 0; i < lottos.size(); i ++) {
            System.out.println(lottos.get(i));
        }
        System.out.println();
    }
}
