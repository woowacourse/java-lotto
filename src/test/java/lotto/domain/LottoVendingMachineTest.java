package lotto.domain;

import lotto.domain.exceptions.InvalidCustomLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoVendingMachineTest {
    @Test
    void validateCountOfCustomLottosTest() {
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        assertThrows(InvalidCustomLottoNumbersException.class, () ->
                LottoVendingMachine.getLottos(new LottoCount(new LottoBuyingMoney(5000),0), numbers));
    }

    @Test
    void getLottosTest() {
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        Lottos lottos = LottoVendingMachine.getLottos(new LottoCount(new LottoBuyingMoney(5000),1), numbers);
        assertThat(lottos.size()).isEqualTo(5);
        assertThat(lottos.getLottos().contains(Lotto.create(
                new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 6))
        ))).isTrue();
    }
}