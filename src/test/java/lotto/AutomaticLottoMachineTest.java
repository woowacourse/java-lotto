package lotto;

import lotto.model.Lotto;
import lotto.model.lottomachine.AutomaticLottoMachine;
import lotto.model.lottomachine.ManualLottoMachine;
import lotto.model.shufflemethod.ShuffleMethod;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AutomaticLottoMachineTest {

    @Test
    public void automaticLottoMachineTest() {
        ShuffleMethod method = all45Numbers -> {
            List<Integer> all45NumbersCopied = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

            return all45NumbersCopied.subList(0, 6);
        };
        Lotto actual = new ManualLottoMachine("1,2,3,4,5,6").generateLotto();
        Lotto expected = new AutomaticLottoMachine(method).generateLotto();
        assertThat(actual).isEqualTo(expected);
    }
}
