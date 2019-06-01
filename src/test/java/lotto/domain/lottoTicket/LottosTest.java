package lotto.domain.lottoTicket;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottosTest {
    @Test
    void 수동로또_개수에따라_자동로또_생성하는_테스트() {
        List<Integer> firstLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> secondLottoNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);
        List<Lotto> manualLottos = Arrays.asList(new ManualLotto(firstLottoNumbers), new ManualLotto(secondLottoNumbers));
        Lottos lottos = new Lottos(manualLottos, 3);
        System.out.println(lottos.toString());
    }
}
