package lotto.domain.lotto;

import lotto.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {
    @Test
    @DisplayName("로또 그룹의 merge를 수행한다.")
    void add_addLottosViaMergingWithOtherLottoGroup() {
        LottoNumbers lottoNumbers1 = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        LottoNumbers lottoNumbers2 = new LottoNumbers(Arrays.asList(
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        ));

        LottoGroup lottoGroup1 = new LottoGroup(Arrays.asList(
                lottoNumbers1
        ));

        LottoGroup lottoGroup2 = new LottoGroup(Arrays.asList(
                lottoNumbers2
        ));

        LottoGroup addedLottoGroup = lottoGroup1.add(lottoGroup2);

        assertThat(addedLottoGroup.getLottos().get(0).getMatchCount(lottoNumbers1)).isEqualTo(6);
        assertThat(addedLottoGroup.getLottos().get(1).getMatchCount(lottoNumbers2)).isEqualTo(6);
    }
}
