package testfixture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.LottoNumber;

public class LottoNumberFixture {

    private LottoNumberFixture() {
    }

    /**
     * 숫자들을 입력받아 List<LottoNumber> 객체를 생성하는 편의 메서드
     *
     * @param numbers 로또 숫자들
     * @return List<LottoNumber>
     */
    public static List<LottoNumber> convertToLottoNumbers(Integer... numbers) {
        return Stream.of(numbers)
            .map(LottoNumber::of)
            .collect(Collectors.toList());
    }
}
