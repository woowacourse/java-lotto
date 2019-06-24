package com.woowacourse.lotto.service;

import java.sql.SQLException;
import java.util.*;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.UserLottoDAO;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.domain.request.LottoGenerateRequest;

public class UserLottoService {
	private static Map<String, Object> model;
	private static UserLottoDAO userLottoDAO = new UserLottoDAO(new DBConnector().getConnection());

	public Map<String, Object> generateLotto(LottoGenerateRequest lottoGenerateRequest) throws SQLException {
		model = new HashMap<>();
		int countOfManualLotto = lottoGenerateRequest.getCountOfManualLotto();
		LottoMoney lottoMoney = lottoGenerateRequest.getLottoMoney();
		LottosFactory lottosFactory = lottoGenerateRequest.getLottosFactory();
		List<String> manualLottos = lottoGenerateRequest.getManualLottos();
		Lottos lottos = lottosFactory.generateLotto(manualLottos);
		List<String> userLottos = new ArrayList<>();
		lottos.getLottos().stream().forEach(x -> userLottos.add(x.toString()));
		addUserLotto(lottos, lottoGenerateRequest.getRound());

		model.put("countOfManualLotto", countOfManualLotto);
		model.put("countOfAutoLotto", lottoMoney.getCountOfLotto() - countOfManualLotto);
		model.put("purchasedLotto", userLottos);
		model.put("lottos", lottos);
		return model;
	}

	private void addUserLotto(Lottos lottos, int round) throws SQLException {
		List<Lotto> lotto = lottos.getLottos();
		for (int i = 0; i < lotto.size(); i++) {
			userLottoDAO.addUserLotto(lotto.get(i), round);
		}
	}
}
