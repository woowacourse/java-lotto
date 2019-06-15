package lotto.domain.lotto;

import lotto.dao.WinningLottoDAO;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class WinningLottoDAOTest {
    @Test
    void DB에_당첨번호를_insert하는지_확인() throws SQLException {
        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.getNumber(7));

        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        winningLottoDAO.addWinningLotto(winningLotto);
    }
}
