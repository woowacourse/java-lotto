package lotto.domain.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoMoney;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.factory.ManualTicketFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameResultDaoTest {
    Connection connection = new DatabaseConnection().getConnection();
    GameResultDao gameResultDao = new GameResultDao(connection);

    @Test
    void 추가_테스트() throws SQLException {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        lottoTicketList.add(new ManualTicketFactory("1,2,3,4,5,6").create());
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        int round = 100;
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);


        LottoMoney lottoMoney = new LottoMoney(2000);
        gameResultDao.addGameResult(lottoTickets, winningLotto, lottoMoney, round);
    }
}