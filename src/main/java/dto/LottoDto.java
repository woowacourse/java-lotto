package dto;

<<<<<<< HEAD
import domain.Lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
=======
import domain.LottoNumber;

>>>>>>> f80fb84 (feat: DTO 추가)
import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
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
    private List<Integer> lottoNumber;

    public LottoDto(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoDto from(List<LottoNumber> lottoNumbers){
        List<Integer> numbers = lottoNumbers.stream()
                .map(LottoNumber::number)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(numbers);
    }
<<<<<<< HEAD
>>>>>>> f80fb84 (feat: DTO 추가)
=======

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
>>>>>>> 7a351e9 (docs: 기능 구현 목록 수정)
}
