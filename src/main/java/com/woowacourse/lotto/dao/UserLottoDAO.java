package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;

import static com.woowacourse.lotto.domain.LottoNumber.NUMBER_OF_LOTTO;


public class UserLottoDAO {
	private final Connection connection;

	public UserLottoDAO(Connection connection) {
		this.connection = connection;
	}

	public void addUserLotto(Lotto lotto, int round) throws SQLException {
		String query = "insert into user_lotto(first_number, second_number, third_number, fourth_number, fifth_number, sixth_number, round)" +
				"VALUES(?,?,?,?,?,?,?)";
		List<Integer> lottoNumber = new ArrayList<>();
		lotto.getNumbers().stream().forEach(number -> lottoNumber.add(number.getName()));

		PreparedStatement pstmt = connection.prepareStatement(query);
		for (int i = 1; i <= lottoNumber.size(); ++i) {
			pstmt.setInt(i, lottoNumber.get(i - 1));
		}
		pstmt.setInt(7, round);
		pstmt.executeUpdate();
	}

	public Lotto findUserLottoById(int userLottoId) throws SQLException {
		String query = "select first_number, second_number, third_number, fourth_number," +
				"fifth_number, sixth_number from user_lotto where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, userLottoId);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int i = 1; i <= NUMBER_OF_LOTTO; ++i) {
			lottoNumbers.add(LottoNumber.getLottoNumber(rs.getInt(i)));
		}
		return new Lotto(lottoNumbers);
	}

	public int getUserLottoCount() throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT count(*) as total from user_lotto");
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return -1;
		}
		return rs.getInt("total");
	}
}

