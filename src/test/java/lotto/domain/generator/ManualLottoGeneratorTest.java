package lotto.domain.generator;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoGeneratorTest {

    @Test
    @DisplayName("수동 로또 번호로 로또를 생성한다.")
    void buyLotto() {
        List<Integer> manualNumbers1 = List.of(8, 21, 23, 41, 42, 43);
        List<Integer> manualNumbers2 = List.of(3, 5, 11, 16, 32, 38);
        List<Integer> manualNumbers3 = List.of(7, 11, 16, 35, 36, 44);

        LottoGenerator generator = new ManualLottoGenerator(List.of(manualNumbers1, manualNumbers2, manualNumbers3));
        List<Lotto> lottos = generator.generateLottos();

        assertThat(lottos)
            .extracting(Lotto::getNumbers)
            .usingRecursiveFieldByFieldElementComparator()
            .contains(
                numbersToSet(manualNumbers1),
                numbersToSet(manualNumbers2),
                numbersToSet(manualNumbers3)
            );
    }

    private Set<LottoNumber> numbersToSet(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
    }

}