package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    private LottoMachine() {
    }

    public static List<LottoTicket> generateAutoLottoTickets(int numOfTickets) {
        return Stream
            .generate(() -> LottoTicket.valueOf(RandomLottoNumberGenerator.generate()))
            .limit(numOfTickets)
            .collect(Collectors.toList());
    }

    public static List<LottoTicket> generateManualLottoTickets(List<String> manualLotto) {
        return manualLotto
            .stream()
            .map(LottoMachine::convertToLottoTicket)
            .collect(Collectors.toList());
    }

    private static LottoTicket convertToLottoTicket(String numbers) {
        return LottoTicket.valueOf(convertToInt(numbers));
    }

    private static List<Integer> convertToInt(String ticket) {
        try {
            return Arrays.stream(ticket.split(","))
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 입력이 아닙니다.");
        }
    }
}
