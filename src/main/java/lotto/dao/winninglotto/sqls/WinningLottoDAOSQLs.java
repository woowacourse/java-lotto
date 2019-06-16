package lotto.dao.winninglotto.sqls;

public class WinningLottoDAOSQLs {
    public static final String SELECT_WINNING_LOTTO_BY_LOTTO_ROUND_ID
            = " SELECT "
            + "  lotto_ticket "
            + ", bonus_number "
            + " FROM winning_lotto "
            + " WHERE "
            + " lotto_round_id = ? ";

    public static final String INSERT_WINNING_LOTTO
            = " INSERT INTO "
            + " winning_lotto ("
            + "  lotto_round_id "
            + ", lotto_ticket "
            + ", bonus_number "
            + " ) "
            + " VALUES ("
            + "  ? "
            + ", ? "
            + ", ? "
            + " ) ";
}
