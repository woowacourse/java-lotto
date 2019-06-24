package com.woowacourse.lotto.controller;

import java.util.HashMap;
import java.util.Map;

import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.domain.request.LottoGenerateRequest;
import com.woowacourse.lotto.service.UserLottoService;
import com.woowacourse.lotto.view.InputViewWeb;
import spark.Request;

import static com.woowacourse.lotto.view.OutputViewWeb.render;

public class UserLottoController {
	private static UserLottoService userLottoService = new UserLottoService();

	public String inputCountOfLotto(Request request) {
		Map<String, Object> model = new HashMap<>();
		int purchasedMoney = Integer.parseInt(request.queryParams("lottoMoney"));
		int countOfManualLotto = Integer.parseInt(request.queryParams("countOfManualLotto"));
		try {
			LottoMoney lottoMoney = new LottoMoney(purchasedMoney);
			request.session().attribute("countOfManualLotto", countOfManualLotto);
			request.session().attribute("lottoMoney", lottoMoney);
			request.session().attribute("lottosFactory", new LottosFactory(lottoMoney, countOfManualLotto));
			return render(model, "/inputWinningLotto.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/index.html");
		}
	}

	public String generateUserLotto(Request request) {
		Map<String, Object> model = new HashMap<>();
		LottoGenerateRequest lottoGenerateRequest = new LottoGenerateRequest();
		lottoGenerateRequest.setCountOfManualLotto(request.session().attribute("countOfManualLotto"));
		lottoGenerateRequest.setLottoMoney(request.session().attribute("lottoMoney"));
		lottoGenerateRequest.setLottosFactory(request.session().attribute("lottosFactory"));
		lottoGenerateRequest.setRound(request.session().attribute("round"));

		if(lottoGenerateRequest.getCountOfManualLotto() != 0) {
			lottoGenerateRequest.setManualLottos(InputViewWeb.splitString(request.queryParams("manualLottos")));
		}

		try {
			model = userLottoService.generateLotto(lottoGenerateRequest);
			request.session().attribute("lottos", model.get("lottos"));
			return render(model, "/showPurchasedLottos.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/inputManualLotto.html");
		}
	}

}
