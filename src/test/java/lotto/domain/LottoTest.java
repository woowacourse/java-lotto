package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성하기")
    void createLotto() {
        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);

        Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(lotto1,lotto2,lotto3,lotto4,lotto5,lotto6));
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }
}
