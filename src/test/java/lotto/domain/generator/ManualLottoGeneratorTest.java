package lotto.domain.generator;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoGeneratorTest {

    @Test
    @DisplayName("수동 로또 번호로 로또를 생성한다.")
    void buyLotto() {
        List<LottoNumber> manualNumbers1 = givenNumbers(8, 21, 23, 41, 42, 43);
        List<LottoNumber> manualNumbers2 = givenNumbers(3, 5, 11, 16, 32, 38);
        List<LottoNumber> manualNumbers3 = givenNumbers(7, 11, 16, 35, 36, 44);

        LottoGenerator generator = new ManualLottoGenerator(List.of(manualNumbers1, manualNumbers2, manualNumbers3));
        List<Lotto> lottos = generator.generateLottos();

        assertThat(lottos)
            .extracting(Lotto::getNumbers)
            .usingRecursiveFieldByFieldElementComparator()
            .contains(
                new HashSet<>(manualNumbers1),
                new HashSet<>(manualNumbers2),
                new HashSet<>(manualNumbers3)
            );
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::of)
            .collect(Collectors.toList());
    }
}