package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lotto.domain.Rank;
import lotto.domain.game.ResultCounter;
import lotto.domain.lotto.Lotto;
import lotto.view.OutputView;

public class WebParser {
    public static List<Lotto> makeLottos(WebUILottoData webUILottoData) {
        return new ArrayList<>(webUILottoData.getTotalLottoGames().getAllGames());
    }

    public static List<String> makeLottoResult(Map<Rank, ResultCounter> lottoResult) {
        List<String> result = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            if (!rank.equals(Rank.MISS)) {
                result.add(OutputView.getRankResult(lottoResult, rank));
            }
        }
        return result;
    }
}
