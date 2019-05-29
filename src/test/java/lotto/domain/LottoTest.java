package lotto.domain;

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
        lotto = Lotto.create(lottoNumbers);
        lottoNumbersInt = Arrays.asList(1,2,3,4,5,6);
    }

    @Test
    void create_생성() {
        assertThat(lotto).isEqualTo(Lotto.create(lottoNumbers));
    }

    @Test
    void create_리스트_개수_예외() {
        lottoNumbers.add(new LottoNumber(7));
        assertThrows(Exception.class, () -> {
            Lotto.create(lottoNumbers);
        });
    }

    @Test
    void create_다른_생성자_생성() {
        assertThat(Lotto.generate(lottoNumbersInt)).isEqualTo(lotto);
    }
}
