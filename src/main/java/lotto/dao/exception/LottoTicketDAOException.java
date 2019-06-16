package lotto.dao.exception;

import java.sql.SQLException;

public class LottoTicketDAOException extends RuntimeException {
    public LottoTicketDAOException(SQLException e) {
        super(e.getMessage() + " -> LottoTicketDAO 에서 발생");
    }
}
