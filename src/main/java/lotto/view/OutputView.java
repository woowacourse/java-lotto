package lotto.view;

import lotto.domain.Lottos;

public class OutputView {
    public static void printLottos(Lottos totalLottos, int manualLottoCount) {
        int randomLottoCount = totalLottos.getLottoCount() - manualLottoCount;
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + randomLottoCount + "개를 구매했습니다.");
        for (int i = 0; i < totalLottos.getLottoCount(); i++) {
            System.out.println(totalLottos.getLottoByIndex(i));
        }
    }
}
