package lotto.dao;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberGroup;
import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.WinningLottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO {
    private static WinningLottoDAO instance;

    private static final String insertQuery = "INSERT INTO winning_lotto (round, num1, num2, num3, num4, num5, num6, bonus_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectQuery = "SELECT * FROM winning_lotto WHERE round = ?";

    private final Connection connection;

    private WinningLottoDAO(Connection connection) {
        this.connection = connection;
    }

    public static WinningLottoDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new WinningLottoDAO(connection);
        }
        return instance;
    }

    public void insertWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        int index = 1;

        pstmt.setInt(index++, round);
        for (LottoNumber number : winningLotto.getWinningNumbers()) {
            pstmt.setInt(index++, number.getNumber());
        }
        pstmt.setInt(index, winningLotto.getBonusNumber().getNumber());

        pstmt.executeUpdate();
    }

    public WinningLottoDTO selectByLottoRound(int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        return (rs.next()) ? getWinningLottoDTO(rs, 2) : new WinningLottoDTO();
    }

    static WinningLottoDTO getWinningLottoDTO(ResultSet rs, int index) throws SQLException {
        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();

        winningLottoDTO.setWinningNumbers(getWinningNumbers(rs, index));
        winningLottoDTO.setBonusNumber(rs.getInt(index + LottoNumberGroup.LOTTO_SIZE));

        return winningLottoDTO;
    }

    static LottoNumberGroup getWinningNumbers(ResultSet rs, int index) throws SQLException {
        List<Integer> lottoNums = new ArrayList<>();
        for (int i = 0; i < LottoNumberGroup.LOTTO_SIZE; i++) {
            lottoNums.add(rs.getInt(index++));
        }
        return LottoNumberGroup.create(() -> lottoNums);
    }
}
