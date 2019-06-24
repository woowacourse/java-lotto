package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserLottoDAOTest {
	private UserLottoDAO userLottoDAO;
	private WinningLottoDAO winningLottoDAO;
	private WinningLotto winningLotto;

	@BeforeEach
	void init() {
		Connection connection = new DBConnector().getConnection();
		userLottoDAO = new UserLottoDAO(connection);
		winningLottoDAO = new WinningLottoDAO(connection);
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		winningLotto = new WinningLotto(numbers, 7);
	}

	@Test
	void addUserLotto() throws SQLException {
		int round = winningLottoDAO.addWinningLotto(winningLotto);
		List<String> lottoNumber = Arrays.asList("1,2,3,4,5,7");
		LottosFactory lottosFactory = new LottosFactory(new LottoMoney(1000), 1);
		Lottos lottos = lottosFactory.generateLotto(lottoNumber);
		List<Lotto> lotto = lottos.getLottos();
		userLottoDAO.addUserLotto(lotto.get(0), round);
		int userLottoId = userLottoDAO.getUserLottoCount();
		assertThat(lotto.get(0)).isEqualTo(userLottoDAO.findUserLottoById(userLottoId));
	}
}