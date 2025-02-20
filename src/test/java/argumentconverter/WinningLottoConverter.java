package argumentconverter;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningLotto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class WinningLottoConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType)
        throws ArgumentConversionException {
        String csvElement = source.toString();
        String[] split = csvElement.split(",");

        List<LottoNumber> lottoNumbers = Arrays.stream(split)
            .map(Integer::parseInt)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        LottoNumber bonusNumber = lottoNumbers.removeLast();
        return new WinningLotto(new Lotto(lottoNumbers), bonusNumber);
    }
}
