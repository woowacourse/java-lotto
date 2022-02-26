package domain;

import domain.Lotto.Lotto;
<<<<<<< HEAD
<<<<<<< HEAD
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
=======
import domain.Lotto.LottoNumberFactory;
=======
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("AutoLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto() {
<<<<<<< HEAD
<<<<<<< HEAD
        Lotto actual = new AutoLottoGenerator().generateLotto();
=======
        Lotto actual = new AutoLottoGenerator().generateLotto(LottoNumberFactory.makeBoundary());
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        Lotto actual = new AutoLottoGenerator().generateLotto();
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("WinningLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateWinningLotto() {
<<<<<<< HEAD
<<<<<<< HEAD
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto actual = new ManualLottoGenerator().generateLotto(lottoNumbers);
=======
        List<String> lottoNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        Lotto actual = new WinningLottoGenerator().generateLotto(LottoNumberFactory.from(lottoNumbers));
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
<<<<<<< HEAD
        Lotto actual = new WinningLottoGenerator().generateLotto(lottoNumbers);
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
=======
        Lotto actual = new WinningLottoGenerator().generateWinningLotto(lottoNumbers);
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

}