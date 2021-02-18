package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {


    @Test
    void 당첨_번호_갯수_체크() {
        String inputNumbers = "1, 2, 3, 4, 5, 6";
        List<Integer> lottoNumbers = Arrays.stream(inputNumbers.split(", ")).map(Integer::parseInt).collect(
            Collectors.toList());
        Lotto lotto = Lotto.generatedBy(lottoNumbers);
    }

}
