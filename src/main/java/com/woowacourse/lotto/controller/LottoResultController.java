package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.service.LottoResultService;
import spark.Request;
import spark.Response;


public class LottoResultController {
	private static final LottoResultService lottoResultService = new LottoResultService();

	public String matchLotto(Request request, Response response) {
		return lottoResultService.matchLotto(request);
	}

	public String getLottoResult(Request request, Response response) {
		return lottoResultService.getLottoResult(request);
	}

	public String searchLottoResult(Request request, Response response) {
		return lottoResultService.searchLottoResult(request);
	}
}
