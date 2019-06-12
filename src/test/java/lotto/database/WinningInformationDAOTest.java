package lotto.database;

import lotto.domain.LottoNumber;
import lotto.domain.WinningInformation;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.exception.RoundNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningInformationDAOTest {
    private WinningInformationDAO winningInformationDAO;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        winningInformationDAO = WinningInformationDAO.getInstance(connection);
        winningInformationDAO.clear();
    }

    @Test
    void addWinningInformation() throws Exception {
        WinningInformation winningInformation = new WinningInformation(
                ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate(), LottoNumber.valueOf(7));
        winningInformationDAO.addWinningInformation(winningInformation);
    }

    @Test
    void findWinningInformationByRound() throws Exception {
        WinningInformation winningInformation = new WinningInformation(
                ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 3, 5, 7, 9, 11)).generate(), LottoNumber.valueOf(22));

        int round = winningInformationDAO.addWinningInformation(winningInformation);
        assertThat(winningInformation).isEqualTo(winningInformationDAO.findWinningInformationByRound(round));
    }

    @Test
    void 찾으려는_회차의_당첨_정보가_없는_경우() {
        assertThrows(RoundNotFoundException.class, () -> winningInformationDAO.findWinningInformationByRound(99));
    }

    @AfterEach
    void tearDown() throws Exception {
        DatabaseConnection.getConnection().close();
    }
}
