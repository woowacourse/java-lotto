package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorManualTest {
    @Test
    void generator() {
        Customer customer = new Customer(5000, 2);
        customer.setManualLottoNumber("1,2,3,4,5,6\n11,12,13,14,15,16");

        List<Lotto> result = new ArrayList<>();
        Lotto lotto1 = new Lotto(IntStream.range(1, 7)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet()));
        Lotto lotto2 = new Lotto(IntStream.range(11, 17)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet()));
        result.add(lotto1);
        result.add(lotto2);

        assertThat(new LottoGeneratorManual().generator(customer)).isEqualTo(result);
    }
}
