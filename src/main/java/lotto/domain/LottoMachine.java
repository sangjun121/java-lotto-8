package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;

public class LottoMachine {
    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }

        return new Lottos(List.copyOf(lottos));
    }

    private Lotto generateLotto() {
        return Lotto.from(numberGenerator.generate());
    }
}
