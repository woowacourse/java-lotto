package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.UserLottos;

public class OutputView {

    public static void printLottos(UserLottos userLottos) {
        System.out.println(userLottos.toString());
    }

    public static void printResult(LottoResult lottoResult) {
        System.out.println(lottoResult.toString());
    }
}
