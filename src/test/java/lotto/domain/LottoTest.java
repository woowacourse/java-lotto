package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.autocreatelotto.MockAutoCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoTest {
    Lotto lotto;
    Lotto lotto2;
    List<LottoNumber> lottoNumbers;
    List<Integer> lottoNumbersInt;

    @BeforeEach
    void setUp() {

        lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
        lottoNumbers.add(new LottoNumber(6));

        lotto = Lotto.createLotto(new MockAutoCreateLotto());

        lotto2 = Lotto.createLotto(lottoNumbers);

        lottoNumbersInt = Arrays.asList(1,2,3,4,5,6);
    }

    @Test
    void create_생성() {
        assertThat(lotto).isEqualTo(lotto2);
    }

    @Test
    void create_리스트_개수_예외() {
        lottoNumbers.add(new LottoNumber(7));
        assertThrows(Exception.class, () -> {
            lotto2.createLotto(lottoNumbers);
        });
    }

    @Test
    void create_로또_중복_확인() {
        lottoNumbers.remove(0);
        lottoNumbers.add(new LottoNumber(6));
        assertThrows(Exception.class, () -> {
            lotto2.createLotto(lottoNumbers);
        });
    }

    @Test
    void match_확인() {
        assertThat(lotto.matchCount(lotto2)).isEqualTo(6);
    }
}
