package dto;

<<<<<<< HEAD
<<<<<<< HEAD
import domain.Lotto.LottoNumber;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
=======
import domain.LottoNumber;
=======
import domain.Lotto.LottoNumber;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)

>>>>>>> f80fb84 (feat: DTO 추가)
=======
import java.util.Collections;
>>>>>>> 3158ddf (refactor : 일급 컬렉션의 List 불변 객체로 만들기)
import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
<<<<<<< HEAD
<<<<<<< HEAD

    private final List<Integer> lottoNumber;

    private LottoDto(List<Integer> lottoNumber) {
        this.lottoNumber = new ArrayList<>(lottoNumber);
    }

    static LottoDto from(List<LottoNumber> lottoNumbers) {
        List<Integer> numbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(numbers);
    }

    public List<Integer> getLottoNumber() {
        return Collections.unmodifiableList(lottoNumber);
    }
=======
=======

>>>>>>> e6945bb (style: 코드 포멧팅)
    private List<Integer> lottoNumber;

    public LottoDto(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoDto from(List<LottoNumber> lottoNumbers) {
        List<Integer> numbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(numbers);
    }
<<<<<<< HEAD
>>>>>>> f80fb84 (feat: DTO 추가)
=======

    public List<Integer> getLottoNumber() {
        return Collections.unmodifiableList(lottoNumber);
    }
>>>>>>> 7a351e9 (docs: 기능 구현 목록 수정)
}
