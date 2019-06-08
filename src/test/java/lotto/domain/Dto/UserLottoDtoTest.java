package lotto.domain.Dto;

import lotto.domain.Exceptions.LottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserLottoDtoTest {
    @Test
    void 구입금액() {
        assertThrows(LottoNumberException.class, () -> {
            new UserLottoDto("a", "1", Arrays.asList("1,2,3,4,5,6"));
        });
    }

    @Test
    void 수동로또개수() {
        assertThrows(LottoNumberException.class, () -> {
            new UserLottoDto("1000", "ㅁ", Arrays.asList("1,2,3,4,5,6"));
        });
    }

    @Test
    void 내부값() {
        UserLottoDto dto = new UserLottoDto("1000", "1", Arrays.asList("1,2,3,4,5,6"));
        assertEquals(1000, dto.getBuyMoney());
        assertEquals(1, dto.getManualCount());
        assertEquals(Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)), dto.getManualNumbers());
    }
}