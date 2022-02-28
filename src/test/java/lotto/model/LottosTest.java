package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1, lotto2;
    private Lottos lottos;

    @BeforeEach
    void init() {
        LottoNumbers lottoNumbers1 = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        LottoNumbers lottoNumbers2 = makeLottoNumbers(new int[]{3, 4, 5, 6, 7, 8});
        lotto1 = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers1);
        lotto2 = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers2);
        lottos = new Lottos(new Money(0));
    }

    @Test
    void 저장_테스트() {
        lottos.insert(lotto1);
        assertThat(lottos.getLottos()).contains(lotto1);
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
