package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LottoGroupTest {

    Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
        LottoNumber.of(1),
        LottoNumber.of(2),
        LottoNumber.of(3),
        LottoNumber.of(4),
        LottoNumber.of(5),
        LottoNumber.of(6)
    ));

    @Test
    void merge() {
        LottoGroup one = new LottoGroup(
            Arrays.asList(new Lotto(lottoNumbers)));
        LottoGroup two = new LottoGroup(
            Arrays.asList(new Lotto(lottoNumbers)));
        LottoGroup lottoGroup = one.merge(two);

        LottoGroup expected = new LottoGroup(
            Arrays.asList(
                new Lotto(lottoNumbers),
                new Lotto(lottoNumbers)));
        assertThat(lottoGroup).isEqualTo(expected);
    }
}
