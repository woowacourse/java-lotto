package domain.generator;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.LottoNumber;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class NumsGeneratorTest {

    @Test
    void generateByRandom_숫자범위_검사() {
        List<LottoNumber> lottoNumbers = NumsGenerator.generateByRandom();
        assertThat(lottoNumbers.listIterator().next())
                .isLessThanOrEqualTo(LottoNumber.getInstance(45))
                .isGreaterThanOrEqualTo(LottoNumber.getInstance(1));
    }

    @Test
    void generateByRandom_개수검사() {
        List<LottoNumber> lottoNumbers = NumsGenerator.generateByRandom();
        assertThat(lottoNumbers.size())
                .isEqualTo(6);
    }

    @Test
    void generate_개수검사() {
        assertThat(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(Arrays.asList(
                                LottoNumber.getInstance(1),
                                LottoNumber.getInstance(2),
                                LottoNumber.getInstance(3),
                                LottoNumber.getInstance(4),
                                LottoNumber.getInstance(5),
                                LottoNumber.getInstance(6)
                        )
                );
    }
}