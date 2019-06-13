package lotto.dao;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class WinningLottoDAOTest {
    @Test
    void 삽입_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLottoDAO.getInstance().insertWinningLotto(1, lotto, bonusNumber);
    }
}
