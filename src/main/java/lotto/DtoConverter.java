package lotto;

import lotto.domain.Lotto;
import lotto.view.LottoDto;
import lotto.view.LottosDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {
    public LottosDto convertLottosToDto(final List<Lotto> lottos) {
        List<LottoDto> semiLottos = new ArrayList<>();
        for (final Lotto lotto : lottos) {
            LottoDto lottoDto = convertLottoToDto(lotto);
            semiLottos.add(lottoDto);
        }
        return LottosDto.of(semiLottos);
    }

    private LottoDto convertLottoToDto(Lotto lotto) {
        LottoDto lottoDto = new LottoDto();
        lottoDto.setNumbers(lotto.getLottoNumbers().stream()
                .map(lottoNumber -> lottoNumber.toString())
                .collect(Collectors.toList()));
        return lottoDto;
    }
}
