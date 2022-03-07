package view;

import dto.LottoDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String JOIN_DELIMITER = ", ";

    private static final String MESSAGE_FORMAT_FOR_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }

    public static void printLottoQuantity(int manualQuantity, int autoQuantity) {
        System.out.printf(System.lineSeparator() + MESSAGE_FORMAT_FOR_LOTTO_COUNT + System.lineSeparator(),
                manualQuantity, autoQuantity);
    }

    public static void printLottos(List<LottoDto> lottoDtos) {
        for (LottoDto lottoDto : lottoDtos) {
            printSingleLotto(lottoDto);
        }
    }

    private static void printSingleLotto(LottoDto lottoDto) {
        System.out.println(OPEN_BRACKET + joinNumbers(lottoDto.getLottoNumbers()) + CLOSE_BRACKET);
    }

    private static String joinNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }
}
