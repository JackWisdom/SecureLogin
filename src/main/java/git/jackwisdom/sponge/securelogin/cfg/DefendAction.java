package git.jackwisdom.sponge.securelogin.cfg;

public enum DefendAction {
    NOTHING, KICK, BAN, BANIP;

    public DefendAction getById(int i) {
        switch (i) {
            case 1:
                return KICK;
            case 2:
                return BAN;
            case 3:
                return BANIP;
            default:
                return NOTHING;
        }
    }
}
