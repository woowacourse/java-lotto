package lotto;

import java.util.List;

public class OutputView {
    public void printLottos(List<LottoDto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (LottoDto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
