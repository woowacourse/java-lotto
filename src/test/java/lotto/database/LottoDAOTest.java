package lotto.database;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.WinningInformation;
import lotto.domain.generator.AutoLottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LottoDAOTest {
    private LottoDAO lottoDAO;
    private WinningInformationDAO winningInformationDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = DatabaseConnection.getConnection();
        lottoDAO = LottoDAO.getInstance(connection);
        winningInformationDAO = WinningInformationDAO.getInstance(connection);
        winningInformationDAO.clear();
    }

    @Test
    void addLotto() throws Exception {
        Lotto lotto = new Lotto(ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate());
        WinningInformation winningInformation = new WinningInformation(
                ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate(), LottoNumber.valueOf(7));
        int round = winningInformationDAO.addWinningInformation(winningInformation);

        lottoDAO.addLotto(lotto, round);
    }

    @Test
    void findLottosByRound() throws Exception {
        Lottos lottos = new Lottos();
        lottos.add(new Lotto(AutoLottoNumbersGenerator.getInstance().generate()));
        lottos.add(new Lotto(AutoLottoNumbersGenerator.getInstance().generate()));
        lottos.add(new Lotto(AutoLottoNumbersGenerator.getInstance().generate()));
        WinningInformation winningInformation = new WinningInformation(
                ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate(), LottoNumber.valueOf(7));

        int round = winningInformationDAO.addWinningInformation(winningInformation);
        lottoDAO.addAllLottos(lottos, round);
        assertThat(lottoDAO.findLottosByRound(round)).isEqualTo(lottos);
    }

    @AfterEach
    void tearDown() throws Exception {
        connection.close();
    }
}
