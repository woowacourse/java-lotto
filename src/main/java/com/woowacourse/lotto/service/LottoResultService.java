package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.LottoResultDAO;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.LottoRank;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.WinningLotto;
import spark.Request;

import static com.woowacourse.lotto.view.OutputView.*;
import static lotto.WebUILottoApplication.render;

public class LottoResultService {
	private static final String NEW_LINE = "<br>";
	private static final String PRINT_SUM = "총 당첨금액은 %d원" + " 입니다.";
	private static Map<String, Object> model;
	private static LottoResultDAO lottoResultDAO = new LottoResultDAO(new DBConnector().getConnection());
	private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO(new DBConnector().getConnection());

	public String matchLotto(Request request) {
		model = new HashMap<>();
		try {
			model = new HashMap<>();
			WinningLotto winningLotto = request.session().attribute("winningLotto");
			LottoResult lottoResult = new LottoResult(winningLotto, request.session().attribute("lottos"));
			int round = request.session().attribute("round");
			LottoMoney lottoMoney = request.session().attribute("lottoMoney");
			long earningRate = lottoMoney.calculateEarningsRate(lottoResult.sum());
			addLottoResult(round, lottoMoney, lottoResult);
			String matchedResult = findLottoResultRankById(round);

			model.put("matchedResult", matchedResult);
			model.put("earningRate", earningRate);
			return render(model, "/showLottoResult.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/showPurchasedLottos.html");
		}
	}

	public String getLottoResult(Request request) {
		model = new HashMap<>();
		try {
			model = new HashMap<>();
			List<Integer> rounds = getLottoRound();
			request.session().attribute("rounds", rounds);

			model.put("rounds", rounds);
			return render(model, "/searchLottoResult.html");
		} catch (Exception e) {
			return render(model, "/index.html");
		}
	}

	public String searchLottoResult(Request request) {
		model = new HashMap<>();
		try {
			model = new HashMap<>();
			int selectedRound = Integer.parseInt(request.queryParams("selectedRound"));
			List<Integer> rounds = request.session().attribute("rounds");
			Map<String, Long> sumAndEarningRate = findLottoResultSumAndEarningRateById(selectedRound);

			model.put("selectNumber", selectedRound);
			model.put("rounds", rounds);
			model.put("ranks", findLottoResultRankById(selectedRound));
			model.put("earningRate", String.format(PRINT_EARNINGS_RATE + NEW_LINE, sumAndEarningRate.get("earningRate")));
			model.put("sum", String.format(PRINT_SUM + NEW_LINE, sumAndEarningRate.get("sum")));
			return render(model, "/searchLottoResult.html");
		} catch (Exception e) {
			return render(model, "/searchLottoResult.html");
		}
	}

	private static void addLottoResult(int round, LottoMoney lottoMoney, LottoResult lottoResult) throws SQLException {
		lottoResultDAO.addLottoResult(round, lottoMoney, lottoResult);
	}

	private static String findLottoResultRankById(int round) throws SQLException {
		Map<LottoRank, Integer> ranks = lottoResultDAO.findLottoResultRankById(round);
		StringBuilder stringBuilder = new StringBuilder();
		printLottoResult(ranks, stringBuilder);
		return stringBuilder.toString();
	}

	private static void printLottoResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder) {
		for (LottoRank lottoRank : ranks.keySet()) {
			printRankResult(ranks, stringBuilder, lottoRank);
		}
	}

	private static void printRankResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder, LottoRank lottoRank) {
		if (lottoRank == LottoRank.SECOND) {
			stringBuilder.append(String.format(PRINT_SECOND_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
			return;
		}
		stringBuilder.append(String.format(PRINT_RESULT_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
	}

	private static List<Integer> getLottoRound() throws SQLException {
		return winningLottoDAO.getLottoRound();
	}

	private static Map<String, Long> findLottoResultSumAndEarningRateById(int selectedRound) throws SQLException {
		return lottoResultDAO.findSumAndEarningRateById(selectedRound);
	}
}
