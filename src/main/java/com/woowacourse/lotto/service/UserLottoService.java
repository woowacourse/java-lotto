package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.*;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.UserLottoDAO;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import spark.Request;

import static lotto.WebUILottoApplication.render;

public class UserLottoService {
	private static final String WHITE_SPACE = "\\r\\n";
	private static Map<String, Object> model;
	private static UserLottoDAO userLottoDAO = new UserLottoDAO(new DBConnector().getConnection());

	public String inputCountOfLotto(Request request) {
		model = new HashMap<>();
		try {
			int purchasedMoney = Integer.parseInt(request.queryParams("lottoMoney"));
			int countOfManualLotto = Integer.parseInt(request.queryParams("countOfManualLotto"));
			LottoMoney lottoMoney = new LottoMoney(purchasedMoney);
			request.session().attribute("countOfManualLotto", countOfManualLotto);
			request.session().attribute("lottoMoney", new LottoMoney(purchasedMoney));
			request.session().attribute("lottosFactory", new LottosFactory(lottoMoney, countOfManualLotto));

			return render(model, "/inputWinningLotto.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/index.html");
		}
	}

	public String generateLotto(Request request) {
		model = new HashMap<>();
		try {
			model = new HashMap<>();
			int countOfManualLotto = request.session().attribute("countOfManualLotto");
			LottoMoney lottoMoney = request.session().attribute("lottoMoney");
			LottosFactory lottosFactory = request.session().attribute("lottosFactory");
			List<String> manualLottos = null;
			if (countOfManualLotto > 0) {
				manualLottos = Arrays.asList(request.queryParams("manualLottos").split(WHITE_SPACE));
			}
			Lottos lottos = lottosFactory.generateLotto(manualLottos);
			List<String> userLottos = new ArrayList<>();
			lottos.getLottos().stream().forEach(x -> userLottos.add(x.toString()));
			addUserLotto(lottos, request.session().attribute("round"));
			request.session().attribute("lottos", lottos);

			model.put("countOfManualLotto", countOfManualLotto);
			model.put("countOfAutoLotto", lottoMoney.getCountOfLotto() - countOfManualLotto);
			model.put("purchasedLotto", userLottos);
			return render(model, "/showPurchasedLottos.html");
		} catch (Exception e) {
			model.put("errorMessage", e.getMessage());
			return render(model, "/inputManualLotto.html");
		}
	}

	private static void addUserLotto(Lottos lottos, int round) throws SQLException {
		List<Lotto> lotto = lottos.getLottos();
		for (int i = 0; i < lotto.size(); i++) {
			userLottoDAO.addUserLotto(lotto.get(i), round);
		}
	}
}
