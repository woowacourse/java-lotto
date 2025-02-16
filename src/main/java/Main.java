import controller.LottoController;
import service.LottoMachine;
import service.LottoNumberGenerator;
import service.LottoWinningChecker;
import view.InputHandler;
import view.OutputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            LottoController lottoController = initializeLottoController(bufferedReader);
            lottoController.start();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("잘못된 입력이 감지되었습니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }

    private static LottoController initializeLottoController(BufferedReader bufferedReader) {
        InputHandler inputHandler = new InputHandler(bufferedReader);
        OutputHandler outputHandler = new OutputHandler();

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new SecureRandom());
        LottoMachine lottoMachine = new LottoMachine(lottoNumberGenerator);

        LottoWinningChecker lottoWinningChecker = new LottoWinningChecker();

        return new LottoController(inputHandler, outputHandler, lottoMachine, lottoWinningChecker);
    }
}
