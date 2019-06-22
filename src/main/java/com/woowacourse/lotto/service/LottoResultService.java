package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dto.LottoRankDTO;
import com.woowacourse.lotto.dao.LottoResultDAO;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.request.LottoSearchRequest;
import com.woowacourse.lotto.domain.request.WinningLottoRequest;

import static com.woowacourse.lotto.view.OutputViewConsole.*;

public class LottoResultService {
	private static final String NEW_LINE = "<br>";
	private static final String PRINT_SUM = "총 당첨금액은 %d원" + " 입니다.";
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

		model.put("matchedResult", matchedResult);
		model.put("earningRate", earningRate);
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
		model.put("earningRate", String.format(PRINT_EARNINGS_RATE + NEW_LINE, sumAndEarningRate.get("earningRate")));
		model.put("sum", String.format(PRINT_SUM + NEW_LINE, sumAndEarningRate.get("sum")));
		return model;
	}

	private void addLottoResult(int round, LottoMoney lottoMoney, LottoResult lottoResult) throws SQLException {
		lottoResultDAO.addLottoResult(round, lottoMoney, lottoResult);
	}

	private String findLottoResultRankById(int round) throws SQLException {
		LottoRankDTO lottoRankDTO = lottoResultDAO.findLottoResultRankById(round);
		return lottoRankDTO.printLottoRank();
	}

	private List<Integer> getLottoRound() throws SQLException {
		return winningLottoDAO.getLottoRound();
	}

	private Map<String, Long> findLottoResultSumAndEarningRateById(int selectedRound) throws SQLException {
		return lottoResultDAO.findSumAndEarningRateById(selectedRound);
	}
}
