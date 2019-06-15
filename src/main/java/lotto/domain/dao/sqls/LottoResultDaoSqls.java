package lotto.domain.dao.sqls;

public class LottoResultDaoSqls {
    public static final String SELECT_ALL_LOTTO_RESULT =
            "SELECT payment_info.round, name, first, second, third, fourth, fifth, miss, payment " +
                    "FROM user " +
                    "INNER JOIN payment_info ON payment_info.user_id = user.id " +
                    "INNER JOIN lotto_result ON lotto_result.round = payment_info.round " +
                    "ORDER BY round DESC " +
                    "LIMIT 10";

    public static final String SELECT_LOTTO_RESULT_BY_ROUND =
            "SELECT payment_info.round, name, first, second, third, fourth, fifth, miss, payment " +
                    "FROM user " +
                    "INNER JOIN payment_info ON payment_info.user_id = user.id " +
                    "INNER JOIN lotto_result ON lotto_result.round = payment_info.round " +
                    "WHERE lotto_result.round = ?";
}
