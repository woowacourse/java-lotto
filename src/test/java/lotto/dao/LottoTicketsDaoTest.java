package lotto.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.factory.ManualTicketFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsDaoTest {
    Connection connection;
    LottoTicketsDao lottoTicketsDao;

    @BeforeEach
    void setUp() {
        connection = new DatabaseConnection().getConnection();
        lottoTicketsDao = new LottoTicketsDao(connection);
    }

    @Test
    void 로또_추가_테스트() throws SQLException {
        connection.setAutoCommit(false);
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
        int round = 100;
        lottoTicketsDao.addLottoTickets(round, lottoTickets);

        assertThat(lottoTicketsDao.findLottoByRound(round)).isEqualTo(lottoTickets);
        connection.rollback();
    }
}