package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    private static final int LOTTO_COUNT = 6;
    private static final String ADD_LOTTO = "INSERT INTO lotto (round, num1, num2, num3, num4, num5, num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LOTTO_BY_ROUND = "SELECT * FROM lotto WHERE round = ?";
    private static final String DELETE_BY_ROUND = "DELETE FROM lotto WHERE round = ?";

    private DataSource dataSource;

    public LottoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addTotalLottos(int round, Lottos lottos) throws SQLException {
        for (int i = 0; i < lottos.getLottoCount(); i++) {
            addLotto(round, lottos.getLottoByIndex(i));
        }
    }

    private void addLotto(int round, Lotto lotto) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAddLotto(round, lotto, con);
        }
    }

    private void executeAddLotto(int round, Lotto lotto, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(ADD_LOTTO)) {
            setPstmtForAddLotto(round, lotto, pstmt);
            pstmt.executeUpdate();
        }
    }

    private void setPstmtForAddLotto(int round, Lotto lotto, PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, round);
        for (int i = 0; i < LOTTO_COUNT; i++) {
            pstmt.setInt(i + 2, lotto.getLottoNumberByIndex(i));
        }
    }

    public List<Lotto> findLottoByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindLotto(round, con);
        }
    }

    private List<Lotto> executeFindLotto(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_LOTTO_BY_ROUND)) {
            pstmt.setInt(1, round);
            return getResultFindLotto(pstmt);
        }
    }

    private List<Lotto> getResultFindLotto(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return getLottos(rs);
        }
    }

    private List<Lotto> getLottos(ResultSet rs) throws SQLException {
        if (!rs.next()) return null;

        List<Lotto> lottos = new ArrayList<>();
        rs.last();
        int rowCount = rs.getRow();
        rs.first();
        for (int i = 0; i < rowCount; i++) {
            lottos.add(new Lotto(getLotto(rs)));
            rs.next();
        }
        return lottos;
    }


    private List<LottoNumber> getLotto(ResultSet rs) throws SQLException {
        List<LottoNumber> lotto = new ArrayList<>();
        for (int i = 1; i <= LOTTO_COUNT; i++) {
            lotto.add(LottoNumber.from(rs.getInt("num" + i)));
        }
        return lotto;
    }

    public void deleteLottos(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeDeleteLottos(round, con);
        }
    }

    private void executeDeleteLottos(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ROUND)) {
            pstmt.setInt(1, round);
            pstmt.executeUpdate();
        }
    }
}