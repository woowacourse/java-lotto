package lotto.database;

import lotto.domain.LottoNumber;
import lotto.domain.WinningInformation;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WinningInformationDAOTest {
    @Test
    void addWinningInformation() throws Exception {
        WinningInformationDAO winningInformationDAO = WinningInformationDAO.getInstance(DatabaseConnection.getConnection());

        WinningInformation winningInformation = new WinningInformation(
                ManualLottoNumbersGenerator.getInstance(Arrays.asList(1, 2, 3, 4, 5, 6)).generate(), LottoNumber.valueOf(7));
        winningInformationDAO.addWinningInformation(winningInformation);
    }
}
