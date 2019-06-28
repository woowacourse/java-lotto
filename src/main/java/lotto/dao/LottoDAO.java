package lotto.dao;

import lotto.database.DBConnector;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.dto.LottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
    private static LottoDAO lottoDAO = new LottoDAO();

    public static LottoDAO getInstance() {
        return lottoDAO;
    }

    public void addLotto(List<LottoDTO> lottoDtos) throws SQLException {
        String query = "INSERT INTO lotto VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection()) {
            for (LottoDTO lottoDto : lottoDtos) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, lottoDto.getRound());
                pstmt.setInt(2, lottoDto.getNumber1());
                pstmt.setInt(3, lottoDto.getNumber2());
                pstmt.setInt(4, lottoDto.getNumber3());
                pstmt.setInt(5, lottoDto.getNumber4());
                pstmt.setInt(6, lottoDto.getNumber5());
                pstmt.setInt(7, lottoDto.getNumber6());
                pstmt.executeUpdate();
            }
        }
    }

    public List<Lotto> findByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return makeLottos(rs);
        }
    }

    private List<Lotto> makeLottos(ResultSet rs) throws SQLException {
        List<Lotto> lottos = new ArrayList<>();

        do {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            lottoNumbers.add(new LottoNumber(rs.getInt("number_1")));
            lottoNumbers.add(new LottoNumber(rs.getInt("number_2")));
            lottoNumbers.add(new LottoNumber(rs.getInt("number_3")));
            lottoNumbers.add(new LottoNumber(rs.getInt("number_4")));
            lottoNumbers.add(new LottoNumber(rs.getInt("number_5")));
            lottoNumbers.add(new LottoNumber(rs.getInt("number_6")));
            lottos.add(new Lotto(lottoNumbers));
        } while (rs.next());

        return lottos;
    }
}
