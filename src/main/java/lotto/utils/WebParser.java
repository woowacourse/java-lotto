package lotto.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lotto.data.WebUILottoData;
import lotto.domain.Rank;
import lotto.domain.game.LottoResult;
import lotto.domain.game.ResultCounter;
import lotto.domain.lotto.Lotto;
import lotto.view.OutputView;

public class WebParser {
    public static List<Lotto> makeLottos(WebUILottoData webUILottoData) {
        return new ArrayList<>(webUILottoData.getTotalLottoGames().allGames());
    }

    public static List<String> result(WebUILottoData webUILottoData) {
        Map<Rank, ResultCounter> lottoResult = LottoResult.create(webUILottoData.getTotalLottoGames(),webUILottoData.getWinningLotto());
        return WebParser.makeLottoResult(lottoResult);
    }

    public static List<String> get() {
        return WebParser.makeLottoResult(LottoResult.get());
    }

    public static String forSQL(List<String> input) {
        StringBuilder sb = new StringBuilder();
        for (String str : input) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }

    private static List<String> makeLottoResult(Map<Rank, ResultCounter> lottoResult) {
        List<String> result = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            if (!rank.equals(Rank.MISS)) {
                result.add(OutputView.getRankResult(lottoResult, rank));
            }
        }
        return result;
    }
}
