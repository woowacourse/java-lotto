package utils;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.LottoNumber;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class NumbersGeneratorTest {

    @Test
    void generateByRandom_숫자범위_검사() {
        List<LottoNumber> lottoNumbers = NumbersGenerator.generateByRandom();
        assertThat(lottoNumbers.listIterator().next())
                .isLessThanOrEqualTo(LottoNumber.from(45))
                .isGreaterThanOrEqualTo(LottoNumber.from(1));
    }

    @Test
    void generateByRandom_개수검사() {
        List<LottoNumber> lottoNumbers = NumbersGenerator.generateByRandom();
        assertThat(lottoNumbers.size())
                .isEqualTo(6);
    }

    @Test
    void generate_개수검사() {
        assertThat(NumbersGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(Arrays.asList(
                                LottoNumber.from(1),
                                LottoNumber.from(2),
                                LottoNumber.from(3),
                                LottoNumber.from(4),
                                LottoNumber.from(5),
                                LottoNumber.from(6)
                        )
                );
    }
}