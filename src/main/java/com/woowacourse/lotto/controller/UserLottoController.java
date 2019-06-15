package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.service.UserLottoService;
import spark.Request;
import spark.Response;

public class UserLottoController {
	private static UserLottoService userLottoService = new UserLottoService();

	public String inputCountOfLotto(Request request, Response response) {
		return userLottoService.inputCountOfLotto(request);
	}

	public String generateUserLotto(Request request, Response response) {
		return userLottoService.generateLotto(request);
	}
}
