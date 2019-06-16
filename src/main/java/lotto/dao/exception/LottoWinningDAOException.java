package lotto.dao.Exception;

import java.sql.SQLException;

public class LottoWinningDAOException extends RuntimeException{
    public LottoWinningDAOException(String message) {
        super(message + " -> 에 의해 LottoWinningDAO에서 오류 발생.");
    }

    public LottoWinningDAOException(SQLException e) {
        super(e.getMessage() + " -> 에 의해 LottoWinningDAO에서 오류 발생.");
    }
}
