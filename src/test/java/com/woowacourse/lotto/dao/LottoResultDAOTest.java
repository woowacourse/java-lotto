package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.dto.LottoRankDTO;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultDAOTest {
	private LottoResultDAO lottoResultDAO;
	private WinningLottoDAO winningLottoDAO;
	private WinningLotto winningLotto;

	@BeforeEach
	void init() {
		Connection connection = new DBConnector().getConnection();
		lottoResultDAO = new LottoResultDAO(connection);
		winningLottoDAO = new WinningLottoDAO(connection);
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		winningLotto = new WinningLotto(numbers, 7);
	}

	@Test
	void addLottoResult() throws SQLException {
		int round = winningLottoDAO.addWinningLotto(winningLotto);
		LottoMoney lottoMoney = new LottoMoney(1000);
		LottosFactory lottosFactory = new LottosFactory(lottoMoney, 1);
		Lottos lottos = lottosFactory.generateLotto(Arrays.asList("1,2,3,4,5,7"));
		LottoResult lottoResult = new LottoResult(winningLotto, lottos);
		lottoResultDAO.addLottoResult(round, lottoMoney, lottoResult);
		int winningLottoId = winningLottoDAO.getWinningLottoCount();

		LottoRankDTO lottoRankDTO = new LottoRankDTO(lottoResult.getRankResult());
		assertThat(lottoRankDTO).isEqualTo(lottoResultDAO.findLottoResultRankById(winningLottoId));
		Map<String, Long> sumAndEarning = lottoResultDAO.findSumAndEarningRateById(winningLottoId);
		assertThat((long) lottoResult.sum()).isEqualTo(sumAndEarning.get("sum"));
		assertThat(lottoMoney.calculateEarningsRate(lottoResult.sum())).isEqualTo(sumAndEarning.get("earningRate"));
	}
}