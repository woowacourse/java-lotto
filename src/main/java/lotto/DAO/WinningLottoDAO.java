package lotto.dao;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLottoDAO {
    private static WinningLottoDAO WinningLottoDAO;
    private Connection con;

    private WinningLottoDAO() {
        con = DAOConnector.getConnection();
    }

    public static WinningLottoDAO getInstance() {
        if (WinningLottoDAO == null) {
            WinningLottoDAO = new WinningLottoDAO();
        }
        return WinningLottoDAO;
    }

    public void insertWinningLotto(int round, List<Integer> lottoNumbers, int bonusNumber) {
        PreparedStatement pstmt = null;
        try {
            String query = "insert into winninglotto values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            for (int i = 0; i < lottoNumbers.size(); i++) {
                pstmt.setInt(i + 2, lottoNumbers.get(i));
            }
            pstmt.setInt(8, bonusNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> findWinningLottoByRound(int round) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<String, String> winningLotto = new HashMap<>();
        try {
            String query = "select * from winninglotto where round = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                List<String> lotto = new ArrayList<>();
                for (int i = 1; i < 7; i++) {
                    lotto.add(rs.getString(i + 1));
                }
                winningLotto.put("winningLotto", StringUtils.join(lotto, ","));
                winningLotto.put("bonusNumber", rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winningLotto;
    }


}
