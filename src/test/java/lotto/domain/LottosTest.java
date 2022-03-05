package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    private List<Lotto> lottoList;
    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = new Lotto(List.of(3, 4, 5, 6, 7, 8));

        lottoList = Arrays.asList(lotto1, lotto2, lotto3);
        lottos = Lottos.of(lottoList, new Money(0));
        winningLotto = new WinningLotto(lotto1, new LottoNumber(7));
    }

    @Test
    @DisplayName("돈을 입력받은 만큼 로또 생성")
    void create_lottos_money() {
        Lottos lottos = Lottos.from(new Money(3650));

        assertEquals(lottos.getLottos().size(), 3);
    }

    @Test
    @DisplayName("수동으로 고른 로또와 남은 돈을 가지고 로또 생성")
    void get_total_size() {
        Lottos lottos = Lottos.of(lottoList, new Money(11000));

        assertEquals(lottos.getLottos().size(), 14);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 1등")
    void match_lottos_first() {
        assertEquals(1, lottos.countLottoRank(winningLotto).get(Rank.FIRST));
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 2등")
    void match_lottos_second() {
        assertEquals(1, lottos.countLottoRank(winningLotto).get(Rank.SECOND));
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 4등")
    void match_lottos_third() {
        assertEquals(1, lottos.countLottoRank(winningLotto).get(Rank.FOURTH));
    }
}
