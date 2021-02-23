package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoNumberGenerator {

    public static List<Integer> generate(List<Integer> numberBox) {
        Collections.shuffle(numberBox);

        return numberBox.stream()
            .limit(LottoTicket.LOTTO_TICKET_SIZE)
            .collect(Collectors.toList());
    }
}
