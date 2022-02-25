package lotto.domain.lottonumbers;

import java.util.HashSet;
import java.util.Set;
import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@VisibleForTesting
class LottoNumbersTest {
    
    @Test
    @DisplayName("이 객체는 방어적 복사를 하기 때문에 컬렉션을 수정하더라도 변경이 없어야 한다")
    void defensive_copy() {
    	// given
        Set<LottoNumber> lottoNumberSet = new HashSet<>();
        lottoNumberSet.add(new LottoNumber(1));
        lottoNumberSet.add(new LottoNumber(2));
        lottoNumberSet.add(new LottoNumber(3));
        lottoNumberSet.add(new LottoNumber(4));
        lottoNumberSet.add(new LottoNumber(5));
        lottoNumberSet.add(new LottoNumber(6));

        LottoNumbers lottoNumbers = new LottoTicket(lottoNumberSet);

        // when
        lottoNumberSet.add(new LottoNumber(7));
    	
    	// then
        Set<LottoNumber> findLottoNumbers = lottoNumbers.lottoNumbers;
        Assertions.assertThat(findLottoNumbers.size()).isEqualTo(6);
    }

}
