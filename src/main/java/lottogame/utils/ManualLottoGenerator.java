package lottogame.utils;

import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");

    private List<String> ticketStrings;

    public ManualLottoGenerator() {
    }

    public ManualLottoGenerator(String ticketString) {
        ticketStrings = new ArrayList<>(Arrays.asList(ticketString));
    }

    private void validate(String numbers) {
        if (!NUMBER_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    @Override
    public List<Lotto> generateLottos() {
        return ticketStrings.stream()
                .map(ticketString -> new Lotto(parse(ticketString)))
                .collect(Collectors.toList());
    }

    private List<LottoNumber> parse(String ticketString) {
        String[] numberStrings = ticketString.split(DELIMITER);
        return Arrays.stream(numberStrings)
                .map(numberString -> LottoNumber.of(Integer.parseInt(numberString)))
                .collect(Collectors.toList());
    }

    public Lotto generateLotto() {
        return new Lotto(parse(ticketStrings.get(0)));
    }

    public void addResources(List<String> ticketStrings) {
        ticketStrings.forEach(this::validate);
        this.ticketStrings = ticketStrings;
    }
}
