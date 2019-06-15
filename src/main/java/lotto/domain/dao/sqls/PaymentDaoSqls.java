package lotto.domain.dao.sqls;

public class PaymentDaoSqls {
    public static final String INSERT_PAYMENT_INFO =
            "INSERT INTO payment_info(payment, manual, auto, user_id) VALUES " +
                    "(?, ?, ?, (SELECT id " +
                                "FROM user " +
                                "WHERE name = ?))";
}
