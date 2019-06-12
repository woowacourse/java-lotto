package lotto.database;

import lotto.domain.LottoNumber;
import lotto.domain.WinningInformation;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.exception.RoundNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningInformationDAO {
    private static final int WINNING_NUMBER_START_INDEX = 1;
    private static final int BONUS_BALL_INDEX = 7;
    private static final int INSERT_ROUND_INDEX = 1;
    private static final int SELECT_ROUND_INDEX = 1;
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
     *
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
        return resultSet.getInt(INSERT_ROUND_INDEX);
    }

    public WinningInformation findWinningInformationByRound(int round) throws SQLException {
        String query = "SELECT * FROM winningInfo WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(SELECT_ROUND_INDEX, round);
        ResultSet resultSet = pstmt.executeQuery();
        if (!resultSet.next()) {
            throw new RoundNotFoundException();
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            numbers.add(resultSet.getInt(i));
        }
        ManualLottoNumbersGenerator manualLottoNumbersGenerator = ManualLottoNumbersGenerator.getInstance(numbers);
        LottoNumber lottoNumber = LottoNumber.valueOf(resultSet.getInt(8));
        return new WinningInformation(manualLottoNumbersGenerator.generate(), lottoNumber);
    }

    public void clear() throws SQLException {
        deleteAllWinningInfo();
        initializeAutoIncrement();
    }

    private void deleteAllWinningInfo() throws SQLException {
        String query = "DELETE FROM winningInfo";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }

    private void initializeAutoIncrement() throws SQLException {
        String query = "ALTER TABLE winningInfo AUTO_INCREMENT=1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }
}
