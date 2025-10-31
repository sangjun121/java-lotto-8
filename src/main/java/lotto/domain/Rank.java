package lotto.domain;

public enum Rank {
    FIRST(6, 2000000000) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == this.getMatchCount();
        }
    },
    SECOND(5, 30000000) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && isBonus;
        }
    },
    THIRD(5, 1500000) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && !isBonus;
        }
    },
    FOURTH(4, 50000) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 4;
        }
    },
    FIFTH(3, 5000) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 3;
        }
    },
    NONE(-1, 0) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count < 3;
        }
    };

    private final int matchCount;
    private final int prizeMoney;

    Rank(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    abstract boolean matches(int count, boolean isBonus);

    public static Rank valueOf(int matchCount, boolean isBonus) {
        for (Rank rank : values()) {
            if (rank.matches(matchCount, isBonus)) {
                return rank;
            }
        }

        return NONE;
    }
}

