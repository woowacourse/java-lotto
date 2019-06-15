package com.woowacourse.lotto.controller;

import java.sql.SQLException;
import java.util.*;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.UserLottoDAO;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import spark.Request;
import spark.Response;

import static lotto.WebUILottoApplication.render;

public class UserLottoController {
	private static Map<String, Object> model;
	private static final String WHITE_SPACE = "\\r\\n";
	private static UserLottoDAO userLottoDAO = new UserLottoDAO(new DBConnector().getConnection());

	public String inputCountOfLotto(Request request, Response response) {
		try {
			model = new HashMap<>();
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

	public String generateUserLotto(Request request, Response response) {
		try {
			model = new HashMap<>();
			int countOfManualLotto = request.session().attribute("countOfManualLotto");
			LottoMoney lottoMoney = request.session().attribute("lottoMoney");
			LottosFactory lottosFactory = request.session().attribute("lottosFactory");
			List<String> list = null;
			if (countOfManualLotto > 0) {
				list = Arrays.asList(request.queryParams("manualLottos").split(WHITE_SPACE));
			}
			Lottos lottos = lottosFactory.generateLotto(list);
			List<String> purchasedLotto = new ArrayList<>();
			lottos.getLottos().stream().forEach(x -> purchasedLotto.add(x.toString()));
			addUserLotto(lottos, request.session().attribute("round"));
			request.session().attribute("lottos", lottos);

			model.put("countOfManualLotto", countOfManualLotto);
			model.put("countOfAutoLotto", lottoMoney.getCountOfLotto() - countOfManualLotto);
			model.put("purchasedLotto", purchasedLotto);
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
