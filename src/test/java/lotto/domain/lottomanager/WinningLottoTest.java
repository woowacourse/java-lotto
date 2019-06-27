package lotto.domain.lottomanager;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    @Test
    public void 보너스번호_중복() {
        assertThrows(IllegalArgumentException.class, () ->{
           new WinningLotto(IntStream.rangeClosed(1, 6)
           .mapToObj(number -> Integer.parseInt(String.valueOf(number)))
                   .collect(Collectors.toList()), 6);
        });
    }
}