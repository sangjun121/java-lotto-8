package lotto.view;

import java.util.List;

public class OutputView {
    private static final String LOTTO_COUNT_MESSAGE = "\n%d개를 구매했습니다.";

    public void printLottoCount(int lottoCount) {
        System.out.printf(LOTTO_COUNT_MESSAGE, lottoCount);
    }

    public void printLottos(String lottos) {
        System.out.printf(lottos);
    }
}
