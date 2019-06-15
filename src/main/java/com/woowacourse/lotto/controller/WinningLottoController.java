package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.service.WinningLottoService;
import spark.Request;
import spark.Response;

public class WinningLottoController {
	private static WinningLottoService winningLottoService = new WinningLottoService();

	public static String inputWinningLotto(Request request, Response response) {
		return winningLottoService.inputWinningLotto(request);
	}
}
