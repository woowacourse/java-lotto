package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1, lotto2;
    private Lottos lottos;

    @BeforeEach
    void init() {
        List<LottoNumber> numbers1 = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        List<LottoNumber> numbers2 = Arrays.asList(new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6),new LottoNumber(7),new LottoNumber(8));
        LottoNumbers lottoNumbers1 = new LottoNumbers(numbers1);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers2);
        lotto1 = new Lotto((minimumNumber, maximumNumber) -> lottoNumbers1);
        lotto2 = new Lotto((x,y) -> lottoNumbers2);
        lottos = new Lottos(new Money(0));
    }

    @Test
    void 저장_테스트() {
        lottos.insert(lotto1);
        assertThat(lottos.getLottos()).contains(lotto1);
    }

    @Test
    void 등수_개수_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        LottoNumbers winningNumbers1 = new LottoNumbers(winningNumbers);
        lottos.insert(lotto1);
        lottos.insert(lotto2);
        lottos.calculateRanks(winningNumbers1, new BonusNumber(7));
        lottos.countRank();
        assertThat(lottos.getCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 수익률_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        LottoNumbers winningNumbers1 = new LottoNumbers(winningNumbers);
        lottos.insert(lotto1); // 1등
        lottos.insert(lotto2); // 4등
        lottos.calculateRanks(winningNumbers1, new BonusNumber(7));
        lottos.countRank();
        assertThat(lottos.getRevenue()).isEqualTo((2000000000 + 50000) / 2000);
    }
}
