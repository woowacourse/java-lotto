package lotto.domain.dao.sqls;

public class LottoDaoSqls {
    public static final String SELECT_ALL_LOTTO_RESULT = "SELECT round.id, name, first, second, third, fourth, fifth, miss " +
            "FROM round " +
            "INNER JOIN lotto_result ON lotto_result.round_id = round.id " +
            "INNER JOIN user ON user.id = round.user_id " +
            // TODO: 2019-06-12 LIMIT?
            "LIMIT 10";

}
