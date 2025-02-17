package view;

import domain.Lotto;
import domain.Numbers;
import domain.Price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InputView {

    public static Price getPrice() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("구입금액을 입력해 주세요.");
        return new Price(bufferedReader.readLine());
    }

    public static Lotto getWinningNumber() throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Numbers numbers = new Numbers(Arrays.stream(bufferedReader.readLine().split(",")).map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        return new Lotto(numbers);
    }

    public static int getBonusNumber() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        return Integer.parseInt(bufferedReader.readLine());
    }

}
