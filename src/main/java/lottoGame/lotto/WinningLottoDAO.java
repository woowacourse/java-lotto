package lottoGame.lotto;

import domain.WinningLotto;
import exception.DataAccessException;
import lottoGame.db.LottoJDBCDriverConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO {
    private final static String DB_TABLE_NAME = "winning_lotto";
    private final static String DB_COLUMN_LABEL_BONUS_NO = "bonus_no";
    private final static String DB_COLUMN_LABEL_NO_1 = "no_1";
    private final static String DB_COLUMN_LABEL_NO_2 = "no_2";
    private final static String DB_COLUMN_LABEL_NO_3 = "no_3";
    private final static String DB_COLUMN_LABEL_NO_4 = "no_4";
    private final static String DB_COLUMN_LABEL_NO_5 = "no_5";
    private final static String DB_COLUMN_LABEL_NO_6 = "no_6";
    private final static String DB_COLUMN_LABEL_TOKEN = "token";
    private static final String DB_COLUMNS =
            DB_COLUMN_LABEL_BONUS_NO + ", "
            + DB_COLUMN_LABEL_NO_1 + ", "
            + DB_COLUMN_LABEL_NO_2 + ", "
            + DB_COLUMN_LABEL_NO_3 + ", "
            + DB_COLUMN_LABEL_NO_4 + ", "
            + DB_COLUMN_LABEL_NO_5 + ", "
            + DB_COLUMN_LABEL_NO_6 + ", "
            + DB_COLUMN_LABEL_TOKEN;

    private static WinningLottoDAO dao = null;

    private WinningLottoDAO() {
    }

    public static WinningLottoDAO getInstance() {
        if (dao == null) {
            dao = new WinningLottoDAO();
        }
        return dao;
    }

    public void add(WinningLotto winningLotto, int token) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", DB_TABLE_NAME, DB_COLUMNS);

        WinningLottoDTO winningLottoDTO = WinningLottoAssembler.makeDTOFrom(winningLotto, token);
        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int parameterIndex = 1;
            pstmt.setInt(parameterIndex++, winningLottoDTO.getBonusNo());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo1());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo2());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo3());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo4());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo5());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getNo6());
            pstmt.setInt(parameterIndex++, winningLottoDTO.getToken());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    public List<WinningLotto> findAll() {
        String sql = String.format("SELECT %s FROM %s ORDER BY token", DB_COLUMNS, DB_TABLE_NAME);

        List<WinningLotto> winningLottos = new ArrayList<>();
        List<Integer> validTokens = findValidTokens();
        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int token = rs.getInt(DB_COLUMN_LABEL_TOKEN);
                if (!validTokens.contains(token)) {
                    continue;
                }
                WinningLottoDTO winningLottoDTO = new WinningLottoDTO(
                        rs.getInt(DB_COLUMN_LABEL_BONUS_NO),
                        rs.getInt(DB_COLUMN_LABEL_NO_1),
                        rs.getInt(DB_COLUMN_LABEL_NO_2),
                        rs.getInt(DB_COLUMN_LABEL_NO_3),
                        rs.getInt(DB_COLUMN_LABEL_NO_4),
                        rs.getInt(DB_COLUMN_LABEL_NO_5),
                        rs.getInt(DB_COLUMN_LABEL_NO_6),
                        rs.getInt(DB_COLUMN_LABEL_TOKEN)
                );
                winningLottos.add(WinningLottoAssembler.makeFrom(winningLottoDTO));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
        return winningLottos;
    }

    public WinningLotto findByToken(int token) {
        String sql = String.format("SELECT * FROM %s WHERE token = %d", DB_TABLE_NAME, token);

        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                WinningLottoDTO winningLottoDTO = new WinningLottoDTO(
                        rs.getInt(DB_COLUMN_LABEL_BONUS_NO),
                        rs.getInt(DB_COLUMN_LABEL_NO_1),
                        rs.getInt(DB_COLUMN_LABEL_NO_2),
                        rs.getInt(DB_COLUMN_LABEL_NO_3),
                        rs.getInt(DB_COLUMN_LABEL_NO_4),
                        rs.getInt(DB_COLUMN_LABEL_NO_5),
                        rs.getInt(DB_COLUMN_LABEL_NO_6),
                        rs.getInt(DB_COLUMN_LABEL_TOKEN)
                );
                return WinningLottoAssembler.makeFrom(winningLottoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }

        return null;
    }

    public List<Integer> findValidTokens() {
        String sql = String.format("SELECT token FROM %s ORDER BY token", DB_TABLE_NAME);

        List<Integer> tokens = new ArrayList<>();
        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                tokens.add(rs.getInt(DB_COLUMN_LABEL_TOKEN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
        return tokens;
    }
}
