package lotto.dao.lotto.sqls;

public class LottoTicketDAOSQLs {
    public static final String SELECT_LOTTO_TICKETS_BY_LOTTO_ROUND_ID
            = " SELECT "
            + " lotto_round_id "
            + ", lotto_ticket "
            + " FROM lotto_ticket "
            + " WHERE "
            + " lotto_round_id = ? ";

    public static final String INSERT_LOTTO_TICKET
            = " INSERT INTO "
            + " lotto_ticket ("
            + "  lotto_round_id "
            + ", lotto_ticket "
            + " ) "
            + " VALUES ("
            + "  ? "
            + ", ? "
            + " ) ";
}
