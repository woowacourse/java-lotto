package lotto.domain;

import lotto.Exception.DuplicateLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoFactoryTest {
    Map<Integer, LottoNumber> map  =  new HashMap<>();

    @BeforeEach
    void setUp() {
        map.put(1,LottoNumberFactory.generateLottoNumber(1));
        map.put(2,LottoNumberFactory.generateLottoNumber(2));
        map.put(3,LottoNumberFactory.generateLottoNumber(3));
        map.put(3,LottoNumberFactory.generateLottoNumber(3));
        map.put(4,LottoNumberFactory.generateLottoNumber(4));
        map.put(5,LottoNumberFactory.generateLottoNumber(5));
    }

    @Test
    void 중복숫자() {
        assertThrows(DuplicateLottoNumberException.class, () -> {
            LottoFactory.generateLotto(map);
        });
    }
}
