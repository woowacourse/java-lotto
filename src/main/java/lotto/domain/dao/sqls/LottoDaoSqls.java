package lotto.domain.dao.sqls;

public class LottoDaoSqls {
    public static final String SELECT_ALL_LOTTO_RESULT =
            "SELECT payment_info.round, name, first, second, third, fourth, fifth, miss, payment " +
                    "FROM user " +
                    "INNER JOIN payment_info ON payment_info.user_id = user.id " +
                    "INNER JOIN lotto_result ON lotto_result.round = payment_info.round " +
                    "LIMIT 10";
    // TODO: 2019-06-12 LIMIT?

    public static final String INSERT_USER =
            "INSERT INTO user (name) " +
                    "  SELECT ? FROM DUAL " +
                    "WHERE NOT EXISTS  " +
                    "  (SELECT name FROM user WHERE name = ?)";
}
