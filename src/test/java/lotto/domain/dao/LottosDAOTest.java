package lotto.domain.dao;

import lotto.db.DatabaseConnection;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;


class LottosDAOTest {
    Connection connection = new DatabaseConnection().getConnection();
    LottosDAO lottosDAO = new LottosDAO(connection);
    List<Lotto> lottos = Arrays.asList(new Lotto(Arrays.asList(1,2,3,4,5,6)));

    @Test
    public void Lottos_추가() throws Exception {
        lottosDAO.addLottos(1,new Lottos(lottos));
    }

}