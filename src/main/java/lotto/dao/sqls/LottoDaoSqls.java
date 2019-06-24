package lotto.dao.sqls;

public class LottoDaoSqls {
    public static final String INSERT_USER =
            "INSERT INTO user (name) " +
                    "  SELECT ? FROM DUAL " +
                    "WHERE NOT EXISTS  " +
                    "  (SELECT name FROM user WHERE name = ?)";

    public static final String INSERT_LOTTO_TICKET =
            "INSERT INTO lotto_ticket(lotto, is_auto, round) " +
                    "VALUES (?, ?, ?)";
    public static final String COMMA_AND_THREE_COLUMNS = ", (?, ?, ?)";

    public static final String INSERT_WINNING_LOTTO =
            "INSERT INTO winning_lotto(round, winning_lotto, bonus_ball) " +
                    "VALUES (?, ?, ?)";

    public static final String INSERT_LOTTO_RESULT =
            "INSERT INTO lotto_result(first, second, third, fourth, fifth, miss, round) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_LOTTO_BY_ROUND =
            "SELECT lotto, is_auto " +
                    "FROM lotto_ticket " +
                    "WHERE round = ?";

    public static final String SELECT_WINNING_LOTTO_BY_ROUND =
            "SELECT winning_lotto, bonus_ball " +
                    "FROM winning_lotto " +
                    "WHERE round = ?";
}
