package lotto.dao;

import lotto.domain.*;
import lotto.dto.WinningLottoDTO;
import lotto.database.DBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private Lotto lotto;
    private WinningLottoDAO winningLottoDao;

    @BeforeEach
    public void setUp() {
        winningLottoDao = new WinningLottoDAO(DBConnector.getConnection());

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    public void addTest() throws SQLException {
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);
        WinningLottoDTO winningLottoDto = winningLotto.toDTO(100);

        winningLottoDao.addWinningLotto(winningLottoDto);
    }

    @Test
    public void findByRoundTest() throws SQLException {
        LottoNumber bonusBall = new LottoNumber(41);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        assertThat(winningLottoDao.findByRound(3)).isEqualTo(winningLotto);
    }
}
