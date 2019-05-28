package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WinnigLottoTest {

    @Test
    void 비교1() {
        WinnigLotto winnigLotto = new WinnigLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertEquals(6, winnigLotto.match(lotto));
    }

    @Test
    void 비교2() {
        WinnigLotto winnigLotto = new WinnigLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));

        assertEquals(0, winnigLotto.match(lotto));
    }
}