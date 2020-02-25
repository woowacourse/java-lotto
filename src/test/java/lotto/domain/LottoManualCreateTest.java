package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoManualCreateTest {
    @Test
    void create() {
        String[] manualLotto = new String[]{"1,2,3,4,5,6", "7,8,9,10,11,12"};
        List<Lotto> result = new ArrayList<>();
        result.add(new Lotto(IntStream
                .range(1, 7)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toList())));
        result.add(new Lotto(IntStream
                .range(7, 13)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toList())));

        assertThat(LottoManualCreate.create(manualLotto)).isEqualTo(result);
    }
}
