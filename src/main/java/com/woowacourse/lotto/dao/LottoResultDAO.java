package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.LottoRank;
import com.woowacourse.lotto.domain.LottoResult;

public class LottoResultDAO {
	private final Connection connection;

	public LottoResultDAO(Connection connection) {
		this.connection = connection;
	}

	public void addLottoResult(int round, LottoMoney lottoMoney, LottoResult lottoResult) throws SQLException {
		String query = "insert into lotto_result(fifth_rank, fourth_rank, third_rank, second_rank, first_rank, winning_lotto_fk, sum, earning_rate)" +
				"values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		List<LottoRank> lottoRanks = lottoResult.getRanks();
		long sumOfLottoMoney = (long) lottoResult.sum();
		for (int i = 1; i <= lottoRanks.size(); ++i) {
			pstmt.setInt(i, lottoResult.valueOf(lottoRanks.get(i - 1)));
		}
		pstmt.setInt(6, round);
		pstmt.setLong(7, sumOfLottoMoney);
		pstmt.setLong(8, lottoMoney.calculateEarningsRate(sumOfLottoMoney));
		pstmt.executeUpdate();
	}

	public Map<LottoRank, Integer> findLottoResultRankById(int selectedRound) throws SQLException {
		String query = "select first_rank, second_rank, third_rank, fourth_rank, fifth_rank from lotto_result where winning_lotto_fk = ?";
		ResultSet rs = findById(query, selectedRound);
		if (!rs.next()) {
			return null;
		}
		Map<LottoRank, Integer> lottoRanks = new TreeMap<>();
		lottoRanks.put(LottoRank.FIRST, rs.getInt(1));
		lottoRanks.put(LottoRank.SECOND, rs.getInt(2));
		lottoRanks.put(LottoRank.THIRD, rs.getInt(3));
		lottoRanks.put(LottoRank.FOURTH, rs.getInt(4));
		lottoRanks.put(LottoRank.FIFTH, rs.getInt(5));
		return lottoRanks;
	}

	public Map<String, Long> findSumAndEarningRateById(int lottoResultId) throws SQLException {
		String query = "select sum, earning_rate from lotto_result where winning_lotto_fk = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, lottoResultId);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		Map<String, Long> result = new HashMap<>();
		result.put("sum", rs.getLong("sum"));
		result.put("earningRate", rs.getLong("earning_rate"));
		return result;
	}

	public ResultSet findById(String query, int id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id);
		return pstmt.executeQuery();
	}
}
