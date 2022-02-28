package dto;

import domain.Lotto;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {

    private final List<LottoNumberDto> lottoDtoNumber;

    public LottoDto(final List<LottoNumberDto> lottoDtoNumbers) {
        this.lottoDtoNumber = lottoDtoNumbers;
    }

    public static LottoDto from(final Lotto lotto) {
        return new LottoDto(lotto.get().stream()
            .map(LottoNumberDto::from)
            .collect(Collectors.toList()));
    }

    public List<LottoNumberDto> get() {
        return lottoDtoNumber;
    }
}
