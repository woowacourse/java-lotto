package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void chosenNumbers_hasSizeOfSix() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getChosenNumbers().size()).isEqualTo(6);
    }

    @Test
    void chosenNumbers_isSortedAsc() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getChosenNumbers()).isSorted();
    }

    @Test
    void manualLotto_passesOnSizeOfSix() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getChosenNumbers().size()).isEqualTo(6);
    }

    @Test
    void manualLotto_failOnSizeNotOfSix() {
        assertThatThrownBy(() -> createNewLotto(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    void manualLotto_isSorted() {
        Lotto lotto = createNewLotto(6, 5, 4, 3, 2, 1);
        assertThat(lotto.getChosenNumbers()).isSorted();
    }

    private Lotto createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
