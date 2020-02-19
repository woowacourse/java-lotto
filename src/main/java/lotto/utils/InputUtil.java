package lotto.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtil {
	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static int readMoney() throws IOException {
		return Integer.parseInt(bufferedReader.readLine());
	}
}
