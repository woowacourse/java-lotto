package lotto.dao;

import lotto.dao.utils.DaoTemplate;
import lotto.domain.dto.PaymentInfoDto;

import java.sql.*;

import static lotto.dao.sqls.LottoDaoSqls.INSERT_USER;
import static lotto.dao.sqls.PaymentDaoSqls.INSERT_PAYMENT_INFO;
import static lotto.dao.utils.JdbcConnector.getConnection;

public class PaymentInfoDao {
    private PaymentInfoDao() {
    }

    private static class PaymentInfoDaoHolder {
        private static final PaymentInfoDao INSTANCE = new PaymentInfoDao();
    }

    public static PaymentInfoDao getInstance() {
        return PaymentInfoDaoHolder.INSTANCE;
    }

    public int insertUser(String name) throws SQLDataException {
        DaoTemplate daoTemplate = (preparedStatement) -> {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
        };

        return daoTemplate.cudTemplate(INSERT_USER);
    }

    public int insertPayment(PaymentInfoDto paymentInfoDTO) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, paymentInfoDTO);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, PaymentInfoDto paymentInfoDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                INSERT_PAYMENT_INFO, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, paymentInfoDTO.getPayment());
        preparedStatement.setInt(2, paymentInfoDTO.getManual());
        preparedStatement.setInt(3, paymentInfoDTO.getAuto());
        preparedStatement.setString(4, paymentInfoDTO.getName());
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
}
