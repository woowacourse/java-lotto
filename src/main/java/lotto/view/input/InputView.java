package lotto.view.input;

import lotto.domain.count.Count;
import lotto.dto.request.LottoTicketDto;
import lotto.dto.request.WinningLottoDto;
import lotto.parser.GameParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static GameParser gameParser = new GameParser();

    public static int inputLottoMoney() {
        System.out.println("구입 금액을 입력하세요");
        String input = scanner.nextLine();
        return gameParser.parseInputToInt(input);
    }

    public static int inputManualCounts() {
        System.out.println("수동으로 구매할 로또 수를 입력 해주세요.");
        String input = scanner.nextLine();
        return gameParser.parseInputToInt(input);
    }

    public static WinningLottoDto inputWinningLotto() {
        Set<Integer> numbers = inputWinningLottoNumber();
        int number = inputBonusNumber();
        return new WinningLottoDto(numbers, number);
    }

    private static Set<Integer> inputWinningLottoNumber() {
        System.out.println("지난주 당첨 번호를 입력하세요");
        String input = scanner.nextLine();
        return gameParser.parseInputToNumbers(input);
    }

    private static int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        String input = scanner.nextLine();
        return gameParser.parseInputToInt(input);
    }

    public static List<LottoTicketDto> inputManualNumber(int manualCount) {
        List<LottoTicketDto> lottoTicketDtos = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해주세요");
        for (int i = 0; i < manualCount; i++) {
            String input = scanner.nextLine();
            LottoTicketDto lottoTicketDto = new LottoTicketDto(gameParser.parseInputToNumbers(input));
            lottoTicketDtos.add(lottoTicketDto);
        }
        return lottoTicketDtos;
    }
}
