package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebOutputView {
    public static String outputLotto(Lotto lotto) {
        String result = "[";
        List<String> lottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(n -> String.valueOf(n.getNumber()))
                .collect(Collectors.toList());
        result += String.join(", ", lottoNumbers);
        return result + "]";
    }

    public static String outputWinningLotto(WinningLotto winningLotto) {
        return outputLotto(winningLotto.getLotto()) + " + " + winningLotto.getBonus().getNumber();
    }


    public static List<String> outputLottos(Lottos lottos) {
        return lottos.getLottos().stream().map(lotto -> outputLotto(lotto)).collect(Collectors.toList());
    }

    public static List<String> outputResult(WinningStatistics winningStatistics) {
        List<String> result = new ArrayList<>();
        Map<Rank, Integer> statistics = winningStatistics.getStatistics();

        for (Rank rank : Rank.values()) {
            if (statistics.get(rank) > 0) {
                if (rank.equals(Rank.MISS)) {
                    result.add(String.format("꽝(%d원): %d회", rank.getWinningMoney(), statistics.get(rank)));
                } else {
                    result.add(String.format("%d등(%d원): %d회", rank.ordinal() + 1, rank.getWinningMoney(), statistics.get(rank)));
                }
            }
        }
        return result;
    }
}
