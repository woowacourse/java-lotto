package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.UserLottos;

import java.util.Map;

public class OutputView {

    public static void printLottos(UserLottos userLottos) {

        System.out.println(userLottos.toString());

    }

    public static void printResult(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Rank, Integer> entry : lottoResult.results().entrySet()) {
            stringBuilder.append(entry.getKey().toString()).append(" - ").append(entry.getValue()).append(" 개\n");
        }
        stringBuilder.append("총 수익률은 ").append(String.format("%.3f", lottoResult.summury())).append("입니다.\n");
        System.out.println("당첨통계\n-------");
        System.out.println(stringBuilder.toString());
    }
}
