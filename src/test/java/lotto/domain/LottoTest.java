package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {

    private Set<LottoNumber> lottoNumbers;
    private Set<LottoNumber> sizeOverLottoNumbers;
    private Set<LottoNumber> sizeLessLottoNumbers;

    @BeforeEach
    void setUp() {
        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);
        LottoNumber lotto7 = new LottoNumber(7);
        lottoNumbers = new HashSet<>(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
        sizeLessLottoNumbers = new HashSet<>(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5));
        sizeOverLottoNumbers = new HashSet<>(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6, lotto7));
    }

    @Test
    @DisplayName("로또 생성하기")
    void createLotto() {
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    @DisplayName("로또는 총 6개의 숫자로 이루어져야한다.")
    void lottoSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(sizeLessLottoNumbers));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(sizeOverLottoNumbers));
    }
}
