package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.List;

public class InputView {
    private static final String PURCHASE_AMOUNT_INPUT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_GUIDE = "당첨 번호를 입력해 주세요.";

    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public int readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_GUIDE);
        String input = readLine();
        return inputParser.parsePurchaseAmount(input);
    }

    public List<Integer> readWinningNumber() {
        System.out.println(WINNING_NUMBER_INPUT_GUIDE);
        String input = readLine();
        return inputParser.parseWinningNumber(input);
    }
}
