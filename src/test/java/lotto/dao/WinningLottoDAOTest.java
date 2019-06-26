package lotto.dao;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    @Test
    void 삽입_테스트() {
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLottoDao.getInstance().insertWinningLotto(1, lotto, bonusNumber);
    }

    @Test
    void 해당_회차에_대한_결과값_출력_테스트() {
        Map<String, String> test = new HashMap<>();
        test.put("winningLotto", "1,2,3,4,5,6");
        test.put("bonusNumber", "20");
        assertThat(WinningLottoDao.getInstance().findWinningLottoByRound(14)).isEqualTo(test);
    }
}
