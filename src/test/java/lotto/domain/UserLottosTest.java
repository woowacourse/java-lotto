package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserLottosTest {

    @Test
    void 생성() {
        UserLottos userLottos = new UserLottos(Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8))));
        assertEquals(2, userLottos.size());

    }

    @Test
    void 비교() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto lotto1 = new WinningLotto(Arrays.asList(3, 5, 7, 9, 11, 13), 10);
        UserLottos userLottos = new UserLottos(Arrays.asList(lotto));
        assertEquals(0, userLottos.match(lotto1));
    }

    @Test
    void 로또_개수() {
        UserLottos userLottos = new UserLottos("3290");
        assertEquals(3, userLottos.size());
    }
}