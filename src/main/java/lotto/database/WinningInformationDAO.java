package lotto.database;

import lotto.domain.WinningInformation;

import java.sql.*;
import java.util.List;

public class WinningInformationDAO {
    private static final int WINNING_NUMBER_START_INDEX = 1;
    private static final int BONUS_BALL_INDEX = 7;
    private static final int ROUND_INDEX = 1;
    private static final String INSERT_WINNING_INFO_QUERY =
            "INSERT INTO winningInfo(" +
            "winning_number1," +
            " winning_number2," +
            " winning_number3," +
            " winning_number4," +
            " winning_number5," +
            " winning_number6," +
            " bonus)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static WinningInformationDAO winningInformationDAO;
    private static Connection connection;

    private WinningInformationDAO() {
    }

    public static WinningInformationDAO getInstance(Connection connection) {
        if (winningInformationDAO == null) {
            winningInformationDAO = new WinningInformationDAO();
        }
        WinningInformationDAO.connection = connection;
        return winningInformationDAO;
    }

    /**
     * 당첨 정보를 데이터베이스에 저장하고 당첨 정보의 회차를 반환한다.
     * @param winningInformation
     * @return 당첨 정보의 회차를 반환
     * @throws SQLException
     */
    public int addWinningInformation(WinningInformation winningInformation) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(INSERT_WINNING_INFO_QUERY, Statement.RETURN_GENERATED_KEYS);
        List<Integer> numbers = winningInformation.getWinningLottoNumbers();
        for (int i = 0; i < numbers.size(); i++) {
            pstmt.setInt(i + WINNING_NUMBER_START_INDEX, numbers.get(i));
        }
        pstmt.setInt(BONUS_BALL_INDEX, winningInformation.getBonusNumber());
        pstmt.executeUpdate();

        ResultSet resultSet = pstmt.getGeneratedKeys();
        if (!resultSet.next()) {
            throw new SQLException();
        }
        return resultSet.getInt(ROUND_INDEX);
    }
}
