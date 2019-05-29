package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    public void 로또_생성_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );
        assertThat(new Lotto(numbers)).isEqualTo(new Lotto(numbers));
    }

    @Test
    public void 로또_생성시_6자리가_아닌_경우에_대한_예외처리_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(numbers);
        });
    }

    @Test
    public void 로또_한장과_당첨_번호의_일치_개수_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );

        List<LottoNumber> winningNumbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(43),
                LottoNumber.get(44),
                LottoNumber.get(45)
        );
        assertThat(new Lotto(numbers).countMatchedNumber(new Lotto(winningNumbers))).isEqualTo(3);
    }
}
