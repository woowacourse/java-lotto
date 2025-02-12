import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {
    private final List<Integer> numbers = new ArrayList<>();

    public WinningNumber(String inputWinningNumbers) {

        String[] winningNumbers = inputWinningNumbers.split(", ");
        if(winningNumbers.length != 6) {
            throw new IllegalArgumentException("당첨 번호의 개수를 6개로 입력해주세요.");
        }

        for(String winningNumber : winningNumbers) {
            try {
                int number = Integer.parseInt(winningNumber);
                if(number < 1 || number > 45) {
                    throw new IllegalArgumentException("당첨 번호는 1~45 사이의 정수로 입력해주세요.");
                }
                if(numbers.contains(number)) {
                    throw new IllegalArgumentException("당첨 번호 간 중복 없이 입력해주세요.");
                }
                 numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("당첨 번호는 정수로 입력해주세요.");
            }
        }
    }
}
