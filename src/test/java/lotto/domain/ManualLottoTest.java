package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoTest {
    @Test
    void 수동_로또_생성하는_테스트() {
        List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        ManualLotto manualLotto = new ManualLotto(inputNumbers);
        assertThat(manualLotto).isEqualTo(new ManualLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }
}
