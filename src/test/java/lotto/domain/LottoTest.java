package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 번호가_6개가_아닌경우() {
        List<LottoNo> lottoNos = LottoNoCollection.createLottoNos();
        assertThrows(IllegalArgumentException.class, () -> Lotto.of(lottoNos));
    }

    @Test
    void 번호_오름차순_정렬_테스트() {
        List<Integer> actual = Arrays.asList(6, 1, 2, 3, 4, 5);
        List<LottoNo> lottoNos = new ArrayList<>();
        actual.forEach(num -> lottoNos.add(LottoNo.from(num)));
        Collections.sort(actual);

        Lotto lotto = Lotto.of(lottoNos);

        assertThat(actual.toString()).isEqualTo(lotto.toString());
    }
}
