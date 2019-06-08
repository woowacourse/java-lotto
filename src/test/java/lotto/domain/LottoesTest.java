package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoesTest {
    private List<Number> lottoNumbers;
    private List<Lotto> lottos;
    private Lottoes lottoes;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new Number(i));
        }

        lottos = new ArrayList<>();
        lottos.add(new Lotto(lottoNumbers));
        lottos.add(new Lotto(lottoNumbers));

        lottoes = new Lottoes(lottos, 4, LottoNumberGenerator.create());
    }

    @Test
    void 내가_구매한_로또_사이즈() {
        assertThat(lottoes.getSize()).isEqualTo(6);
    }

    @Test
    void 인덱스에_맞는_객체() {
        assertThat(lottoes.getIndexByLotto(0)).isEqualTo(new Lotto(lottoNumbers));

    }
}
