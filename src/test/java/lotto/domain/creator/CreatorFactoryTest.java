package lotto.domain.creator;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatorFactoryTest {
    @Test
    void 수동_로또_크리에이터가_개수만큼_잘_생성되는지_확인() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 12");
        CreatorFactory factory = new CreatorFactory(inputs);
        assertThat(factory.createCreators(2, 0).size()).isEqualTo(2);
    }

    @Test
    void 자동_로또_크리에이터가_개수만큼_잘_생성되는지_확인() {
        List<String> inputs = new ArrayList<>();
        CreatorFactory factory = new CreatorFactory(inputs);
        assertThat(factory.createCreators(0, 5).size()).isEqualTo(5);
    }

    @Test
    void 전체_로또_크리에이터가_개수만큼_잘_생성되는지_확인() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 12", "3, 4, 5, 6, 7, 8");
        CreatorFactory factory = new CreatorFactory(inputs);
        assertThat(factory.createCreators(3, 1).size()).isEqualTo(4);
    }

    @Test
    void 입력한_수동_로또_수와_로또_숫자_수가_다른_경우_예외_반환() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 12");
        CreatorFactory factory = new CreatorFactory(inputs);
        assertThrows(InvalidLottoNumbersException.class,
                () -> factory.createCreators(3, 6));

    }
}
