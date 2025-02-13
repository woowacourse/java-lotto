import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.lotto.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class RandomNumberGeneratorTest {
    @DisplayName("로또번호생성기_셔플_테스트")
    @Test
    void 로또번호생성기_테스트() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<Integer> numbers = randomNumberGenerator.generateLottoNumbers();
        assertEquals(numbers.size(), 6);
    }
}