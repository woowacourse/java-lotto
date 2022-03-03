package domain.service.impl;

import static java.util.stream.Collectors.toList;

import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.service.LottoService;
import java.util.List;
import view.InputView;

public class ManualLottoServiceImpl implements LottoService {
    @Override
    public List<LottoNumber> getLottoNumbers() {
        try {
            return InputView.scanManualLottoNumbers()
                    .stream()
                    .map(LottoNumberRepository::getLottoNumberByInt)
                    .collect(toList());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getLottoNumbers();
        }
    }
}
