package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lottos lottos = makeLottos();

    @Test
    void Lottos_생성_테스트() {
        assertThat(lottos).isInstanceOf(Lottos.class);
    }

    @Test
    void 등수_계산_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        LottoNumber bonusNumber = new LottoNumber(7);
        Map<Rank, Integer> rankCount = new LinkedHashMap<>();
        rankCount.put(Rank.FIRST, 1);
        rankCount.put(Rank.SECOND, 1);
        rankCount.put(Rank.FOURTH, 1);
        assertThat(lottos.calculateRanks(winningNumbers, bonusNumber)).containsAllEntriesOf(rankCount);
    }

    @Test
    void Lottos_크기_테스트() {
        assertThat(lottos.size()).isEqualTo(3);
    }

    private Lottos makeLottos() {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = makeLotto(new int[]{1, 2, 3, 4, 5, 6}); //1등
        Lotto lotto1 = makeLotto(new int[]{2, 3, 4, 5, 6, 7}); //2등
        Lotto lotto2 = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        lottos.add(lotto);
        lottos.add(lotto1);
        lottos.add(lotto2);
        return new Lottos(lottos);
    }

    private Lotto makeLotto(int[] numbers) {
        LottoNumbers lottoNumbers = makeLottoNumbers(numbers);
        return new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers);
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
