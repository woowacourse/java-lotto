package lotto.service;

import lotto.domain.*;
import lotto.utils.Converter;
import lotto.utils.ResultMessage;
import lotto.utils.ViewUtils;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {

    public static Route makeLottoResultPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        Lotto lotto = new Lotto(Converter.convertNumbers(req.queryParams("winningLotto")));
        LottoNumber bonusNo = LottoNumber.valueOf(Integer.parseInt(req.queryParams("bonusNumber")));
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);
        LottoResult lottoResult = LottoResult.generateLottoResult(req.session().attribute("lottos"), winningLotto);
        Price price = req.session().attribute("price");
        model.put("yield", lottoResult.findYield(price.getPrice()));
        model.put("userLottoResult", ResultMessage.getResult(lottoResult, getRanks()));

        return ViewUtils.render(model, "result.html");
    };

    private static List<Rank> getRanks() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIRST);
        ranks.add(Rank.SECOND);
        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);
        return ranks;
    }
}
