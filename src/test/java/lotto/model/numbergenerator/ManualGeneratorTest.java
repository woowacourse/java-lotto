package lotto.model.numbergenerator;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualGeneratorTest {

    @Test
    @DisplayName("수동 구매 번호를 정렬시켜 반환하는지 확인한다")
    void sortTest() {
        List<Set<Integer>> result = List.of(Set.of(43, 42, 41, 23, 21, 8));
        ManualGenerator manualGenerator = new ManualGenerator(result);

        Set<Integer> expected = Set.of(8, 21, 23, 41, 42, 43);
        assertThat(manualGenerator.generate()).isEqualTo(expected);
    }
}
