package lotto.dao;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static lotto.dao.DBUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketDAOTest {
    LottoTicketGroup lottos;
    LottoTicketDAO lottoTicketDAO;
    Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = getConnection();
        connection.setAutoCommit(false);
        lottoTicketDAO = LottoTicketDAO.getInstance(connection);
        lottos = new LottoTicketGroup(Arrays.asList(LottoTicket.create(()->Arrays.asList(1, 2, 3, 4, 5, 6))));
    }

    //에러 발생1 (해당 라운드 값이 round 테이블에 존재하지 않아 외래키가 없음)
    @Test
    public void insertTest1() {
        assertThrows(SQLException.class, () ->
                lottoTicketDAO.insertLottoTickets(100, lottos)
        );
    }

    //정상 실행 (외래키 존재, 해당 라운드 당첨 로또 존재하지 않음)
    @Test
    public void insertTest3() {
        assertDoesNotThrow(() -> {
            LottoRoundDAO.getInstance(connection).insertRound(200);
            lottoTicketDAO.insertLottoTickets(200, lottos);
        });
    }

    //해당 라운드 존재하는 경우
    @Test
    public void selectTest1() throws Exception {
        LottoRoundDAO.getInstance(connection).insertRound(200);
        lottoTicketDAO.insertLottoTickets(200, lottos);

        LottoTicketGroup lottoTickets = lottoTicketDAO.selectByLottoRound(200);

        lottoTickets.iterator().forEachRemaining(x -> assertThat(x).isNotNull());
    }

    //존재하지 않는 경우
    @Test
    public void selectTest2() throws SQLException {
        LottoTicketGroup lottoTickets = lottoTicketDAO.selectByLottoRound(200);

        lottoTickets.iterator().forEachRemaining(x -> assertThat(x).isNull());
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}