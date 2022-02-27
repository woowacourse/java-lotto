package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.Rank;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1, lotto2;
    private Lottos lottos;

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }

    @BeforeEach
    void init() {
        LottoNumbers lottoNumbers1 = makeLottoNumbers(new int[]{1,2,3,4,5,6});
        LottoNumbers lottoNumbers2 = makeLottoNumbers(new int[]{3,4,5,6,7,8});
        lotto1 = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers1);
        lotto2 = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers2);
        lottos = new Lottos(new Money(0));
    }

    @Test
    void 저장_테스트() {
        lottos.insert(lotto1);
        assertThat(lottos.getLottos()).contains(lotto1);
    }

    @Test
    void 등수_개수_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1,2,3,4,5,6});
        lottos.insert(lotto1);
        lottos.insert(lotto2);
        lottos.calculateRanks(winningNumbers, new BonusNumber(7));
        lottos.countRank();
        assertThat(lottos.getEachRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 수익률_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1,2,3,4,5,6});
        lottos.insert(lotto1); // 1등
        lottos.insert(lotto2); // 4등
        lottos.calculateRanks(winningNumbers, new BonusNumber(7));
        lottos.countRank();
        assertThat(lottos.getRevenue()).isEqualTo((2000000000 + 50000) / 2000);
    }
}
