package lottoGame.lotto;

import domain.Lotto;
import domain.LottoGroup;
import exception.DataAccessException;
import lottoGame.db.LottoJDBCDriverConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoDAO {
    private final static String DB_TABLE_NAME = "lotto";
    private final static String DB_COLUMN_LABEL_IDX = "idx";
    private final static String DB_COLUMN_LABEL_NO_1 = "no_1";
    private final static String DB_COLUMN_LABEL_NO_2 = "no_2";
    private final static String DB_COLUMN_LABEL_NO_3 = "no_3";
    private final static String DB_COLUMN_LABEL_NO_4 = "no_4";
    private final static String DB_COLUMN_LABEL_NO_5 = "no_5";
    private final static String DB_COLUMN_LABEL_NO_6 = "no_6";
    private final static String DB_COLUMN_LABEL_TOKEN = "token";
    private final static String DB_COLUMN_LABEL_IS_AUTO = "is_auto";
    private static final String DB_COLUMNS =
            DB_COLUMN_LABEL_IDX + ", "
                    + DB_COLUMN_LABEL_NO_1 + ", "
                    + DB_COLUMN_LABEL_NO_2 + ", "
                    + DB_COLUMN_LABEL_NO_3 + ", "
                    + DB_COLUMN_LABEL_NO_4 + ", "
                    + DB_COLUMN_LABEL_NO_5 + ", "
                    + DB_COLUMN_LABEL_NO_6 + ", "
                    + DB_COLUMN_LABEL_TOKEN + ", "
                    + DB_COLUMN_LABEL_IS_AUTO;

    private static LottoDAO dao = null;

    private LottoDAO() {
    }

    public static LottoDAO getInstance() {
        if (dao == null) {
            dao = new LottoDAO();
        }
        return dao;
    }

    public void add(Lotto lotto, int token, int idx, boolean isAuto) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", DB_TABLE_NAME, DB_COLUMNS);

        LottoDTO lottoDTO = LottoAssembler.makeDTOFrom(lotto, token, idx, isAuto);
        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int parameterIndex = 1;
            pstmt.setInt(parameterIndex++, lottoDTO.getIdx());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo1());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo2());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo3());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo4());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo5());
            pstmt.setInt(parameterIndex++, lottoDTO.getNo6());
            pstmt.setInt(parameterIndex++, lottoDTO.getToken());
            pstmt.setBoolean(parameterIndex++, lottoDTO.isAuto());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    // TODO: batch 사용
    public void addLottoGroup(LottoGroup lottoGroup, int token) {
        for (int idx = 0; idx < lottoGroup.nonRandomSize(); idx++) {
            add(lottoGroup.get(idx), token, idx, false);
        }

        for (int idx = lottoGroup.nonRandomSize(); idx < lottoGroup.totalSize(); idx++) {
            add(lottoGroup.get(idx), token, idx, true);
        }
    }

    public Lotto find(int token, int idx) {
        String sql = String.format("SELECT %s FROM %s WHERE token = %d AND idx = %d", DB_COLUMNS, DB_TABLE_NAME, token, idx);

        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                LottoDTO lottoDTO = new LottoDTO(
                        rs.getInt(DB_COLUMN_LABEL_IDX),
                        rs.getInt(DB_COLUMN_LABEL_NO_1),
                        rs.getInt(DB_COLUMN_LABEL_NO_2),
                        rs.getInt(DB_COLUMN_LABEL_NO_3),
                        rs.getInt(DB_COLUMN_LABEL_NO_4),
                        rs.getInt(DB_COLUMN_LABEL_NO_5),
                        rs.getInt(DB_COLUMN_LABEL_NO_6),
                        rs.getInt(DB_COLUMN_LABEL_TOKEN),
                        rs.getBoolean(DB_COLUMN_LABEL_IS_AUTO)
                );
                return LottoAssembler.makeFrom(lottoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }

        return null;
    }

    public List<LottoGroup> findAllLottoGroup() {
        // validTokens 이 현재 WinningLottoDAO 에서 가져오고 있음
        // 이는 WinningLottoDAO 까지 진행되었으면 해당 라운드에 필요한 모든 정보가 DB에 저장되었음이 보장되었기 때문
        List<Integer> validTokens = WinningLottoDAO.getInstance().findValidTokens();
        System.out.println("validTokens: " + Arrays.toString(validTokens.toArray()));
        List<LottoGroup> lottoGroups = new ArrayList<>();

        for (int token : validTokens) {
            lottoGroups.add(findLottoGroup(token));
        }

        return lottoGroups;
    }

    public LottoGroup findLottoGroup(int token) {
        String sql = String.format("SELECT %s FROM %s WHERE token = %d ORDER BY idx", DB_COLUMNS, DB_TABLE_NAME, token);

        List<Lotto> nonRandomLottos = new ArrayList<>();
        List<Lotto> randomLottos = new ArrayList<>();
        try (Connection conn = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LottoDTO lottoDTO = new LottoDTO(
                        rs.getInt(DB_COLUMN_LABEL_IDX),
                        rs.getInt(DB_COLUMN_LABEL_NO_1),
                        rs.getInt(DB_COLUMN_LABEL_NO_2),
                        rs.getInt(DB_COLUMN_LABEL_NO_3),
                        rs.getInt(DB_COLUMN_LABEL_NO_4),
                        rs.getInt(DB_COLUMN_LABEL_NO_5),
                        rs.getInt(DB_COLUMN_LABEL_NO_6),
                        rs.getInt(DB_COLUMN_LABEL_TOKEN),
                        rs.getBoolean(DB_COLUMN_LABEL_IS_AUTO)
                );
                Lotto lotto = LottoAssembler.makeFrom(lottoDTO);
                if (lottoDTO.isAuto()) {
                    randomLottos.add(lotto);
                } else {
                    nonRandomLottos.add(lotto);
                }
                System.out.println(lotto.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
        return LottoGroup.of(nonRandomLottos, randomLottos);
    }
}
