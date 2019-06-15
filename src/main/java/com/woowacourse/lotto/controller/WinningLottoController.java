package com.woowacourse.lotto.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.utils.StringSeparator;
import spark.Request;
import spark.Response;

import static lotto.WebUILottoApplication.render;

public class WinningLottoController {
	private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO(new DBConnector().getConnection());
	private static Map<String, Object> model = new HashMap<>();

	public static String inputWinningLotto(Request request, Response response) {
		try {
			int bonusBall = Integer.parseInt(request.queryParams("bonusBall"));
			int countOfManualLotto = request.session().attribute("countOfManualLotto");
			List<String> winningLottoNumbers = StringSeparator.splitString(request.queryParams("winningLotto"));
			WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusBall);
			int round = addWinningLotto(winningLotto);
			request.session().attribute("round", round);
			request.session().attribute("winningLotto", winningLotto);

			model.put("countOfManualLotto", countOfManualLotto);
			return render(model, "/inputManualLotto.html");
		} catch (Exception e) {
			return render(model, "/inputWinningLotto.html");
		}
	}

	private static int addWinningLotto(WinningLotto winningLotto) throws SQLException {
		return winningLottoDAO.addWinningLotto(winningLotto);
	}

}
