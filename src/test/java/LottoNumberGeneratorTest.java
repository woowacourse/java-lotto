import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoNumberGenerator;


public class LottoNumberGeneratorTest {
    @DisplayName("로또번호생성기_셔플_테스트")
    @Test
    void 로또번호생성기_테스트() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<Integer> numbers = lottoNumberGenerator.generateLottoNumbers();
        assertEquals(numbers.size(), 6);
    }
}