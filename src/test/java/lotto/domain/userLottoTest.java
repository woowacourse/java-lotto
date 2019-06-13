package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class userLottoTest {
    private List<Number> lottoNumbers;
    private UserLotto userLotto;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(Number.of(i));
        }

        List<Lotto> allLotto = new ArrayList<>();
        allLotto.add(new Lotto(lottoNumbers));
        allLotto.add(new Lotto(lottoNumbers));

        userLotto = new UserLotto(allLotto, 4, new LottoNumberGenerator());
    }

    @Test
    void 내가_구매한_로또_사이즈() {
        assertThat(userLotto.getSize()).isEqualTo(6);
    }

    @Test
    void 인덱스에_맞는_객체() {
        assertThat(userLotto.getIndexByLotto(0)).isEqualTo(new Lotto(lottoNumbers));
    }
}
