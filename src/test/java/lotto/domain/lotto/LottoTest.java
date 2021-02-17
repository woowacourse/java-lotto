package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void 로또_번호_길이_테스트() {
        // given, when
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5)
            .stream()
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList());

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.generatedBy(() -> lottoNumbers);
        });
    }

    @Test
    void 로또_번호_중복_테스트() {
        // given, when
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 1, 2, 3, 4, 5)
            .stream()
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList());

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.generatedBy(() -> lottoNumbers);
        });
    }

    @RepeatedTest(10)
    void 로또_번호_랜덤_생성_테스트() {
        // given, when
        Lotto lotto1 = Lotto.generatedBy(new RandomLottoGenerator());

        // then
        lotto1.getNumbers().stream().map(number -> number.getValue()).forEach(number -> {
            System.out.print(number + " ");
        });
        System.out.println();
    }
}
