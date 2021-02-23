package lottogame.view;

import lottogame.domain.dto.LottoDto;
import lottogame.utils.InvalidBonusBallNumberException;
import lottogame.utils.InvalidManualTicketQuantityException;
import lottogame.utils.InvalidMoneyException;
import lottogame.utils.InvalidWinningLottoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner;
    private static final Pattern LOTTO_NUMBER_INPUT_PATTERN =
            Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String input() {
        return scanner.nextLine();
    }

    public int inputMoney() {
        try {
            System.out.println("구입 금액을 입력해 주세요.");
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException();
        }
    }

    public List<Integer> inputWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return inputLottoNumbers();
    }

    private List<Integer> inputLottoNumbers() {
        String numbers = input();
        if (!LOTTO_NUMBER_INPUT_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
        return Arrays.stream(numbers.split(", "))
                .mapToInt(number -> Integer.parseInt(number))
                .boxed()
                .collect(Collectors.toList());
    }

    public List<LottoDto> inputManualLotto(int autoTicketQuantity) {
        System.out.println("\n수동으로 구매할 로또 번호를 입력해 주세요.");
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (int i=0; i<autoTicketQuantity; i++) {
            LottoDto lottoDto = LottoDto.Of(inputLottoNumbers());
            lottoDtos.add(lottoDto);
        }
        return lottoDtos;
    }

    public int inputBonusNumber() {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new InvalidBonusBallNumberException();
        }
    }

    public int inputManualQuantity() {
        try {
            System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new InvalidManualTicketQuantityException();
        }
    }
}
