package domain.service.impl;

import static java.util.stream.Collectors.toList;

import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.service.LottoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoServiceImpl implements LottoService {
    @Override
    public List<LottoNumber> getLottoNumbers() {
        List<Integer> lottoNumbersForNewLotto = new ArrayList<>(lottoNumbers);
        Collections.shuffle(lottoNumbersForNewLotto);

        return lottoNumbersForNewLotto.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .sorted()
                .map(LottoNumberRepository::getLottoNumberByInt)
                .collect(toList());
    }
}
