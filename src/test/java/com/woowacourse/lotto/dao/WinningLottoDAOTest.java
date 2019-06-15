package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoDAOTest {
	private WinningLottoDAO winningLottoDAO;
	private WinningLotto winningLotto;

	@BeforeEach
	void init() {
		Connection connection = new DBConnector().getConnection();
		winningLottoDAO = new WinningLottoDAO(connection);
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		winningLotto = new WinningLotto(numbers, 7);
	}

	@Test
	void addWinningLotto() throws SQLException {
		winningLottoDAO.addWinningLotto(winningLotto);
		int round = winningLottoDAO.getWinningLottoCount();
		WinningLotto expected = winningLottoDAO.findWinningLottoByRound(round);
		assertThat(winningLotto).isEqualTo(expected);
	}

	@Test
	void getLottoRounds() throws SQLException {
		int lottoResultId = winningLottoDAO.getWinningLottoCount();
		assertTrue(lottoResultId == winningLottoDAO.getLottoRound().size());
	}
}