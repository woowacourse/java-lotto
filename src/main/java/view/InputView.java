package view;

<<<<<<< HEAD
<<<<<<< HEAD
import domain.Lotto.WinningLotto;
import domain.player.Money;
import utils.InputValidator;

=======
>>>>>>> 31d7c6e (feat: WinningLotto 객체 생성)
=======
import utils.InputValidator;

>>>>>>> 701f681 (refactor: 입력 유효성 검사 static으로 변경 및 적용)
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
<<<<<<< HEAD

=======
>>>>>>> 31d7c6e (feat: WinningLotto 객체 생성)

public class InputView {

    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private static final InputView inputView = new InputView();
=======
    private static String input() {
=======
    private static final InputView inputView = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return inputView;
    }

    private String input() {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        String input = scanner.nextLine();
        InputValidator.validateNull(input);
        InputValidator.validateEmpty(input);
        return input;
    }
>>>>>>> 701f681 (refactor: 입력 유효성 검사 static으로 변경 및 적용)

    private InputView() {
    }

    public static InputView getInstance() {
        return inputView;
    }

<<<<<<< HEAD
    private String input() {
        String input = scanner.nextLine();
        InputValidator.validateNull(input);
        InputValidator.validateEmpty(input);
        return input;
    }

    public int inputPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return Integer.parseInt(input());
=======
=======

>>>>>>> 31d7c6e (feat: WinningLotto 객체 생성)
    public static int inputPurchaseAmount() {
=======
    public int inputPurchaseAmount() {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
<<<<<<< HEAD
        return Integer.parseInt(scanner.nextLine());
>>>>>>> 1f14e05 (feat: Money 객체 생성)
    }

<<<<<<< HEAD
    public List<Integer> inputWinningNumber() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        List<String> numberValues = toStrings(input());
        return toNumbers(numberValues);
=======
        return Integer.parseInt(input());
>>>>>>> 701f681 (refactor: 입력 유효성 검사 static으로 변경 및 적용)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    private List<String> toStrings(String stringArray) {
=======
    public static List<String> inputWinningNumber() {
=======
    public static List<Integer> inputWinningNumber() {
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
=======
    public List<Integer> inputWinningNumber() {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        List<String> numberValues = toStrings(input());
        return toNumbers(numberValues);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    private static List<String> toStringList(String stringArray) {
>>>>>>> 31d7c6e (feat: WinningLotto 객체 생성)
=======
    private static List<String> toStrings(String stringArray) {
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
=======
    private List<String> toStrings(String stringArray) {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        return Arrays.stream(stringArray.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private List<Integer> toNumbers(List<String> numberValues) {
=======
    private static List<Integer> toNumbers(List<String> numberValues) {
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
=======
    private List<Integer> toNumbers(List<String> numberValues) {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        return numberValues.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public int inputBonusBall() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return Integer.parseInt(input());
=======
    public static int inputBonusBall(){
=======
=======
>>>>>>> dbac179 (refactor : List<String>을 List<Integer> 로 변환해주는 역할 View에 위임)
    public static int inputBonusBall() {
>>>>>>> 31d7c6e (feat: WinningLotto 객체 생성)
=======
    public int inputBonusBall() {
>>>>>>> 62a4f7d (refactor : InputView, OutputView 싱글턴 패턴으로 관리)
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
<<<<<<< HEAD
        return Integer.parseInt(scanner.nextLine());
>>>>>>> 1f14e05 (feat: Money 객체 생성)
=======
        return Integer.parseInt(input());
>>>>>>> 701f681 (refactor: 입력 유효성 검사 static으로 변경 및 적용)
    }
}
