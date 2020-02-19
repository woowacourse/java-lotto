package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberGeneratorTest {
    @DisplayName("갯수만큼 복권 번호 생성하는 테스트")
    @Test
    public void generateLottoNumbersTest() {
        int number = 14;
        List<Integer> lottonumber = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottonumber.add(i);
        }
    }
}
