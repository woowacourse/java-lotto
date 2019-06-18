package lotto.dao.sqls;

public class WinningLottoSQLs {
    public static final String INSERT_WINNING_LOTTO =
            "INSERT INTO winning_lotto (number_1, number_2, number_3,number_4, number_5, number_6, bonus_number) " +
                    "VALUES(?,?,?,?,?,?,?)";
}
