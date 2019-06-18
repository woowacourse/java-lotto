package lotto.dao.sqls;

public class LottoSQLs {
    public static final String INSERT_LOTTO =
            "INSERT INTO lotto (round, number_1, number_2, number_3, number_4,number_5,number_6) " +
                    "VALUES(?,?,?,?,?,?,?)";
    public static final String SELECT_LOTTO_BY_ROUND =
            "SELECT * " +
                    "FROM lotto " +
                    "WHERE round = ?";
}
