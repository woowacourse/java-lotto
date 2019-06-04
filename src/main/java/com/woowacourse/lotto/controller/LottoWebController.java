package com.woowacourse.lotto.controller;

import com.google.gson.Gson;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.controller.dto.LottoBuyingRequestDto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoWebController {

    public static String index(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("minBuyingMoney", Lotto.UNIT_PRICE);
        return render(model, "index.html");
    }

    public static Map<String, Object> buyLotto(Request req, Response res) {
        try {
            LottoBuyingRequestDto request = new Gson().fromJson(req.body(), LottoBuyingRequestDto.class);
            BuyingMoney bm = new BuyingMoney(request.getBuyingMoney());
            List<Lotto> lottos = new ArrayList<>();
            LottoQuantity numOfLotto = bm.getQuantity();

            request.getManualNumbers().forEach(
                nums -> lottos.add(LottoFactory.createLotto(LottoNumber.of(nums)))
            );
            final int numOfManualLottos = lottos.size();

            NumberGenerator numberGenerator = new RandomNumberGenerator(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);
            for (int i = 0; i < numOfLotto.toInt() - numOfManualLottos; i++) {
                lottos.add(LottoFactory.createLotto(numberGenerator));
            }

            WinningLotto winningLotto = new WinningLotto(LottoNumber.of(request.getWinningNumbers()), request.getWinningBonusNumber());

            WinningAggregator aggregator = new WinningAggregator();
            lottos.forEach(l -> aggregator.addResult(winningLotto.match(l)));
            Map<String, Integer> rankCountMap = new HashMap<>();
            rankCountMap.put("first", aggregator.getResultCount(LottoResult.FIRST));
            rankCountMap.put("second", aggregator.getResultCount(LottoResult.SECOND));
            rankCountMap.put("third", aggregator.getResultCount(LottoResult.THIRD));
            rankCountMap.put("fourth", aggregator.getResultCount(LottoResult.FOURTH));
            rankCountMap.put("fifth", aggregator.getResultCount(LottoResult.FIFTH));
            rankCountMap.put("none", aggregator.getResultCount(LottoResult.NONE));

            Map<String, Object> aggregationMap = new HashMap<>();
            aggregationMap.put("rankCounts", rankCountMap);
            aggregationMap.put("earningRate", aggregator.calculateEarningRate(Lotto.UNIT_PRICE));
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("result", "ok");
            resMap.put("aggregation", aggregationMap);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("result", "error");
            resMap.put("message", e.getMessage());
            return resMap;
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
