package lotto.dao.winninglotto.sqls;

public class WinningResultDAOSQLs {
    public static final String SELECT_WINNING_RESULT_BY_LOTTO_ROUND_ID
            = " SELECT "
            + "  first "
            + ", second "
            + ", third "
            + ", fourth "
            + ", fifth "
            + ", miss "
            + ", roi "
            + " FROM winning_result "
            + " WHERE "
            + " lotto_round_id = :lottoRoundId ";

    public static final String INSERT_WINNING_RESULT
            = " INSERT INTO "
            + " winning_result ("
            + "  lotto_round_id "
            + ", first "
            + ", second "
            + ", third "
            + ", fourth "
            + ", fifth "
            + ", miss "
            + ", roi "
            + " ) "
            + " VALUES ("
            + "  :lottoRoundId "
            + ", :first "
            + ", :second "
            + ", :third "
            + ", :fourth "
            + ", :fifth "
            + ", :miss "
            + ", :roi "
            + " ) ";
}
