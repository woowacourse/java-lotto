package lotto.domain.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.factory.ManualTicketFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsDaoTest {
    Connection connection = new DatabaseConnection().getConnection();
    LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);

    @Test
    void 로또_추가_테스트() throws SQLException {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        lottoTicketsDao.addLottoTickets(lottoTickets);
    }

    @Test
    void 로또_확인_테스트() throws SQLException {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        assertThat(lottoTicketsDao.findByRoundLotto("1")).isEqualTo(lottoTickets);
    }

}