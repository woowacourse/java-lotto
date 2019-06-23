package com.woowacourse.lotto.controller;

import java.util.HashMap;
import java.util.Map;

import com.woowacourse.lotto.domain.request.WinningLottoInputRequest;
import com.woowacourse.lotto.service.WinningLottoService;
import com.woowacourse.lotto.utils.StringSeparator;
import spark.Request;

import static com.woowacourse.lotto.view.OutputViewWeb.render;

public class WinningLottoController {
	private static WinningLottoService winningLottoService = new WinningLottoService();

	public String inputWinningLotto(Request request) {
		Map<String, Object> model = new HashMap<>();

		WinningLottoInputRequest winningLottoInputRequest = new WinningLottoInputRequest();
		winningLottoInputRequest.setBonusBall(Integer.parseInt(request.queryParams("bonusBall")));
		winningLottoInputRequest.setCountOfManualLotto(request.session().attribute("countOfManualLotto"));
		winningLottoInputRequest.setWinningLotto(StringSeparator.splitString(request.queryParams("winningLotto")));
		try {
			model = winningLottoService.inputWinningLotto(winningLottoInputRequest);
			request.session().attribute("round", model.get("round"));
			request.session().attribute("winningLotto", model.get("winningLotto"));
		return render(model, "/inputManualLotto.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/inputWinningLotto.html");
		}
	}
}
