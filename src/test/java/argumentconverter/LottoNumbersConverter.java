package argumentconverter;

import domain.LottoNumber;
import java.util.Arrays;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class LottoNumbersConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType)
        throws ArgumentConversionException {
        String csvElement = source.toString();
        String[] split = csvElement.split(",");
        return Arrays.stream(split)
            .map(Integer::parseInt)
            .map(LottoNumber::new)
            .toList();
    }
}
