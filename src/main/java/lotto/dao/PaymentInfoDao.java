package lotto.dao;

import lotto.dao.utils.DaoTemplate;
import lotto.service.dto.PaymentInfoDto;

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

    public int insertUser(String name) throws SQLException {
        DaoTemplate daoTemplate = (preparedStatement) -> {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
        };

        return daoTemplate.cudTemplate(INSERT_USER);
    }

    public int insertPayment(PaymentInfoDto paymentInfoDto) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, paymentInfoDto);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            return resultSet.getInt(1);
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, PaymentInfoDto paymentInfoDto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                INSERT_PAYMENT_INFO, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, paymentInfoDto.getPayment());
        preparedStatement.setInt(2, paymentInfoDto.getManual());
        preparedStatement.setInt(3, paymentInfoDto.getAuto());
        preparedStatement.setString(4, paymentInfoDto.getName());
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
}
