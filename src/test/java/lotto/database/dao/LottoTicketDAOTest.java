package lotto.database.dao;

import lotto.database.JdbcConnector;
import lotto.domain.machine.Purchase;
import lotto.domain.ticket.LottoTicketFactory;
import lotto.domain.ticket.LottoTickets;
import lotto.dto.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

class LottoTicketDAOTest {
    private Connection con;
    private LottoTicketDAO lottoTicketDAO;
    private PurchaseDTO purchaseDTO;

    @BeforeEach
    public void setUp() throws Exception {
        this.con = JdbcConnector.getConnection();
        this.lottoTicketDAO = new LottoTicketDAO(con);
        con.setAutoCommit(false);

        LottoTickets lottoTickets = LottoTicketFactory.of(Purchase.of(5, 0, Arrays.asList()));
        purchaseDTO = new PurchaseDTO(1, 0, 5, lottoTickets);
    }

    @Test
    public void 랜덤_5장_추가() throws SQLException {
        lottoTicketDAO.addLottoTickets(purchaseDTO);
    }

    @AfterEach
    void tearDown() throws SQLException {
        con.rollback();
        con.close();
    }

}