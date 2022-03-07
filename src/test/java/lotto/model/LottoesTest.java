package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoesTest {
    private Lotto[] lottoes = null;

    @BeforeEach
    void setUp() {
        lottoes = new Lotto[]{
            Lotto.create(List.of(1,2,3,4,5,6)),
            Lotto.create(List.of(11,12,13,14,15,16)),
            Lotto.create(List.of(21,22,23,24,25,26)),
            Lotto.create(List.of(31,32,33,34,35,36))
        };
    }

    @Test
    void combine() {
        Lottoes lottoes1 = new Lottoes(List.of(lottoes[0], lottoes[1]));
        Lottoes lottoes2 = new Lottoes(List.of(lottoes[2], lottoes[3]));

        Lottoes actual = lottoes1.combine(lottoes2);

        assertThat(actual).hasSize(4);
        assertThat(actual).contains(lottoes);
    }

    @Test
    void getPrice() {
        Lottoes lotto = new Lottoes(List.of(lottoes[0], lottoes[1], lottoes[2]));
        assertThat(lotto.getPrice()).isEqualTo(Lotto.PRICE.multiply(3));
    }

}
