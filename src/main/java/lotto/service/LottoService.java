package lotto.service;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottos generateLotto(PurchaseAmount purchaseAmount) {
        return lottoMachine.generateLottos(purchaseAmount.calculateLottoCount());
    }
}
