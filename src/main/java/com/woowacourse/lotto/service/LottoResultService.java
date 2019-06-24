package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.LottoResultDAO;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.request.LottoSearchRequest;
import com.woowacourse.lotto.domain.request.WinningLottoRequest;

public class LottoResultService {
	private static Map<String, Object> model;
	private static LottoResultDAO lottoResultDAO = new LottoResultDAO(new DBConnector().getConnection());
	private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO(new DBConnector().getConnection());

	public Map<String, Object> matchLotto(WinningLottoRequest winningLottoRequest) throws SQLException {
		model = new HashMap<>();
		WinningLotto winningLotto = winningLottoRequest.getWinningLotto();
		LottoResult lottoResult = new LottoResult(winningLotto, winningLottoRequest.getLottos());
		int round = winningLottoRequest.getRound();
		LottoMoney lottoMoney = winningLottoRequest.getLottoMoney();
		long earningRate = lottoMoney.calculateEarningsRate(lottoResult.sum());

		addLottoResult(round, lottoMoney, lottoResult);
		String matchedResult = findLottoResultRankById(round);

		model.put("ranks", matchedResult);
		model.put("earningRate", earningRate);
		model.put("sum", (long) lottoResult.sum());
		return model;
	}

	public Map<String, Object> getLottoResult() throws SQLException {
		List<Integer> rounds = getLottoRound();
		model = new HashMap<>();
		model.put("rounds", rounds);
		return model;
	}

	public Map<String, Object> searchLottoResult(LottoSearchRequest lottoSearchRequest) throws SQLException {
		model = new HashMap<>();
		int selectedRound = lottoSearchRequest.getSelectedRound();
		List<Integer> rounds = lottoSearchRequest.getRounds();
		Map<String, Long> sumAndEarningRate = findLottoResultSumAndEarningRateById(selectedRound);

		model.put("selectNumber", selectedRound);
		model.put("rounds", rounds);
		model.put("ranks", findLottoResultRankById(selectedRound));
		model.put("earningRate", sumAndEarningRate.get("earningRate"));
		model.put("sum", sumAndEarningRate.get("sum"));
		return model;
	}

	private void addLottoResult(int round, LottoMoney lottoMoney, LottoResult lottoResult) throws SQLException {
		lottoResultDAO.addLottoResult(round, lottoMoney, lottoResult);
	}

	private String findLottoResultRankById(int round) throws SQLException {
		return lottoResultDAO.findLottoResultRankById(round).printLottoRank();
	}

	private List<Integer> getLottoRound() throws SQLException {
		return winningLottoDAO.getLottoRound();
	}

	private Map<String, Long> findLottoResultSumAndEarningRateById(int selectedRound) throws SQLException {
		return lottoResultDAO.findSumAndEarningRateById(selectedRound);
	}
}
