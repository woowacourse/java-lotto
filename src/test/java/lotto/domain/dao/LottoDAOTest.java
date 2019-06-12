package lotto.domain.dao;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAOTest {
    private LottoDAO lottoDAO;

    @Before
    public void setUp() {
        lottoDAO = new LottoDAO();
    }

    @Test
    public void addLotto() {
        List<Number> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(NumberSet.of(i));
        }
        int round = 2;
        try {
            lottoDAO.addLotto(new Lotto(lottoNumbers), round);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
