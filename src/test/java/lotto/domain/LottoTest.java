package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    private Set<LottoNumber> numbers;

    @BeforeEach
    public void setUp() {
        numbers = new HashSet<>(Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        ));
    }

    @Test
    public void 로또_같은_조합_번호_다른_순서_일치_여부_테스트() {
        Set<LottoNumber> differentOrderedNumbers = new HashSet<>(
                Arrays.asList(
                        LottoNumber.get(2),
                        LottoNumber.get(1),
                        LottoNumber.get(6),
                        LottoNumber.get(5),
                        LottoNumber.get(4),
                        LottoNumber.get(3)
                ));
        assertThat(new Lotto(numbers)).isEqualTo(new Lotto(differentOrderedNumbers));
    }

    @Test
    public void 로또_생성시_6자리가_아닌_경우에_대한_예외처리_테스트() {
        Set<LottoNumber> notEnoughNumbers = new HashSet<>(
                Arrays.asList(
                        LottoNumber.get(1),
                        LottoNumber.get(2),
                        LottoNumber.get(3),
                        LottoNumber.get(4),
                        LottoNumber.get(5)
                ));
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(notEnoughNumbers);
        });
    }

    @Test
    public void 로또_한장과_당첨_번호의_일치_개수_테스트() {
        Set<LottoNumber> winningNumbers = new HashSet<>(
                Arrays.asList(
                        LottoNumber.get(1),
                        LottoNumber.get(2),
                        LottoNumber.get(3),
                        LottoNumber.get(43),
                        LottoNumber.get(44),
                        LottoNumber.get(45)
                ));
        assertThat(new Lotto(numbers).countMatchedNumber(new Lotto(winningNumbers))).isEqualTo(3);
    }
}
