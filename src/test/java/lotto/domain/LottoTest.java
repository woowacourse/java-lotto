package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {

    @Test
    void 올바른_로또_생성() {
        assertDoesNotThrow(() ->
                new Lotto(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 45))));
    }

    @Test
    void 갯수가_유효하지_않은_로또_생성_검증() {
        assertThrows(InvalidLottoException.class,
                () -> new Lotto(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 45))));
    }

    @Test
    void 중복된_수가_있는_로또_생성_검증() {
        assertThrows(InvalidLottoException.class, () ->
                new Lotto(new HashSet(Arrays.asList(1, 2, 3, 4, 5, 45))));
    }
}
