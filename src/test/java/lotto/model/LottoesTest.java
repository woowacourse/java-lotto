package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoesTest {
    private static final Lotto[] LOTTOES = new Lotto[]{
        Lotto.create(List.of(1,2,3,4,5,6)),
        Lotto.create(List.of(11,12,13,14,15,16)),
        Lotto.create(List.of(21,22,23,24,25,26)),
        Lotto.create(List.of(31,32,33,34,35,36))
    };

    @Test
    void combine() {
        Lottoes lottoes1 = new Lottoes(List.of(LOTTOES[0], LOTTOES[1]));
        Lottoes lottoes2 = new Lottoes(List.of(LOTTOES[2], LOTTOES[3]));

        Lottoes actual = lottoes1.combine(lottoes2);

        assertThat(actual).hasSize(4);
        assertThat(actual).contains(LOTTOES);
    }

    @Test
    void getPrice() {
        Lottoes lottoes = new Lottoes(List.of(LOTTOES[0], LOTTOES[1], LOTTOES[2]));
        assertThat(lottoes.getPrice()).isEqualTo(Lotto.PRICE.multiply(3));
    }

}
