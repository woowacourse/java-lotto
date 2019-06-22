package com.woowacourse.lotto.controller;

import java.util.HashMap;
import java.util.Map;

import com.woowacourse.lotto.domain.request.LottoSearchRequest;
import com.woowacourse.lotto.domain.request.WinningLottoRequest;
import com.woowacourse.lotto.service.LottoResultService;
import com.woowacourse.lotto.view.OutputViewWeb;
import spark.Request;


import static com.woowacourse.lotto.view.OutputViewWeb.render;


public class LottoResultController {
	private static final LottoResultService lottoResultService = new LottoResultService();

	public String matchLotto(Request request) {
		Map<String, Object> model = new HashMap<>();
		WinningLottoRequest winningLottoRequest = new WinningLottoRequest();
		winningLottoRequest.setWinningLotto(request.session().attribute("winningLotto"));
		winningLottoRequest.setLottos(request.session().attribute("lottos"));
		winningLottoRequest.setRound(request.session().attribute("round"));
		winningLottoRequest.setLottoMoney(request.session().attribute("lottoMoney"));

		try {
			model = lottoResultService.matchLotto(winningLottoRequest);
			return render(model, "/showLottoResult.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/showPurchasedLottos.html");
		}
	}

	public String getLottoResult(Request request) {
		Map<String, Object> model = new HashMap<>();
		try {
			model = lottoResultService.getLottoResult();
			request.session().attribute("rounds", model.get("rounds"));
			return render(model, "/searchLottoResult.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/index.html");
		}
	}

	public String searchLottoResult(Request request) {
		Map<String, Object> model = new HashMap<>();
		LottoSearchRequest lottoSearchRequest = new LottoSearchRequest();
		lottoSearchRequest.setSelectedRound(Integer.parseInt(request.queryParams("selectedRound")));
		lottoSearchRequest.setRounds(request.session().attribute("rounds"));

		try {
			model = lottoResultService.searchLottoResult(lottoSearchRequest);
			return OutputViewWeb.printLottoResult(model);
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/searchLottoResult.html");
		}
	}
}
