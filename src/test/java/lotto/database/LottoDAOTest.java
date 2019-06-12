package lotto.database;

import lotto.domain.Lotto;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoDAOTest {
    @Test
    void addLotto() throws Exception {
        LottoDAO lottoDAO = LottoDAO.getInstance(DatabaseConnection.getConnection());
        Lotto lotto = new Lotto(ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate());
        lottoDAO.addLotto(lotto, 1);
    }
}
