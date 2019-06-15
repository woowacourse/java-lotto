package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.WinningLotto;

import static com.woowacourse.lotto.domain.LottoNumber.NUMBER_OF_LOTTO;

public class WinningLottoDAO {
	private final Connection connection;

	public WinningLottoDAO(Connection connection) {
		this.connection = connection;
	}

	public WinningLotto findWinningLottoByRound(int round) throws SQLException {
		String query = "select first_number, second_number, third_number, fourth_number," +
				"fifth_number, sixth_number, bonus_ball from winning_lotto where round = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, round);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		List<String> winningLotto = new LinkedList<>();
		for (int i = 1; i <= NUMBER_OF_LOTTO; ++i) {
			winningLotto.add(rs.getString(i));
		}
		return new WinningLotto(winningLotto, rs.getInt("bonus_ball"));
	}

	public int addWinningLotto(WinningLotto winningLotto) throws SQLException {
		String query = "INSERT INTO winning_lotto(first_number, second_number, third_number, fourth_number, fifth_number, sixth_number,  bonus_ball)" +
				" VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		List<LottoNumber> lottoNumbers = winningLotto.getWinningLotto();
		for (int i = 1; i <= lottoNumbers.size(); i++) {
			pstmt.setInt(i, lottoNumbers.get(i - 1).getName());
		}
		pstmt.setInt(7, winningLotto.getBonusBall());
		pstmt.executeUpdate();
		return getWinningLottoCount();
	}

	public int getWinningLottoCount() throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT count(*) as total from winning_lotto");
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return -1;
		}
		return rs.getInt("total");
	}

	public List<Integer> getLottoRound() throws SQLException {
		String query = "select round from winning_lotto";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery(query);
		List<Integer> rounds = new ArrayList<>();
		while (rs.next()) {
			rounds.add(rs.getRow());
		}
		return rounds;
	}
}
