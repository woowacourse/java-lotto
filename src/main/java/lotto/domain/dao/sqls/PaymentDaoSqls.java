package lotto.domain.dao.sqls;

public class PaymentDaoSqls {
    public static final String INSERT_PAYMENT_INFO =
            "INSERT INTO payment_info(payment, user_id, manual, auto) VALUES " +
                    "(?, ?, ?, (SELECT id " +
                                "FROM user " +
                                "WHERE name = ?))";
}
