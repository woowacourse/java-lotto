package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.domain.request.WinningLottoInputRequest;

public class WinningLottoService {
	private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO(new DBConnector().getConnection());

	public Map<String, Object> inputWinningLotto(WinningLottoInputRequest winningLottoInputRequest) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		int bonusBall = winningLottoInputRequest.getBonusBall();
		int countOfManualLotto = winningLottoInputRequest.getCountOfManualLotto();
		List<String> winningLottoNumbers = winningLottoInputRequest.getWinningLotto();
		WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusBall);
		int round = addWinningLotto(winningLotto);

		model.put("countOfManualLotto", countOfManualLotto);
		model.put("round", round);
		model.put("winningLotto", winningLotto);
		return model;
	}

	private int addWinningLotto(WinningLotto winningLotto) throws SQLException {
		return winningLottoDAO.addWinningLotto(winningLotto);
	}
}
