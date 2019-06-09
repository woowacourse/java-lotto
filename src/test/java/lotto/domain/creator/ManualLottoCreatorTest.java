package lotto.domain.creator;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottoCreatorTest {
    @Test
    void 입력값에_숫자가_아닌_값이_포함된_경우_예외_반환() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, a");
        assertThrows(InvalidLottoNumbersException.class, () -> new ManualLottoCreator(inputs));
    }

    @Test
    void 입력한_갯수만큼_로또가_생성되는지_확인() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, 16");
        ManualLottoCreator creator = new ManualLottoCreator(inputs);
        assertThat(creator.createLottos(2).size()).isEqualTo(2);
    }

    @Test
    void 입력한_갯수와_입력한_로또_번호_수가_다를_경우_예외_반환() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, 16");
        ManualLottoCreator creator = new ManualLottoCreator(inputs);
        assertThrows(InvalidLottoNumbersException.class, () -> creator.createLottos(3));
    }
}
