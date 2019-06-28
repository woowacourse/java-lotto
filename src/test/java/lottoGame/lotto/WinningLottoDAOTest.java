package lottoGame.lotto;

import domain.LottoGenerator;
import domain.Number;
import domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDAOTest {

    @Test
    void _2개_추가_후_확인() {
        WinningLottoDAO dao = WinningLottoDAO.getInstance();
        int token1 = 10;
        int token2 = 11;
        List<WinningLotto> winningLottos = Arrays.asList(
                WinningLotto.of(LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)), Number.from(43)),
                WinningLotto.of(LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)), Number.from(43)));

        dao.add(winningLottos.get(0), token1);
        dao.add(winningLottos.get(1), token2);

        assertThat(winningLottos.get(0)).isEqualTo(dao.findByToken(token1));
        assertThat(winningLottos.get(1)).isEqualTo(dao.findByToken(token2));
    }
}