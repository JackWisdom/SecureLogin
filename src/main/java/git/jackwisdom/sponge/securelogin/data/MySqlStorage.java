package git.jackwisdom.sponge.securelogin.data;

public class MySqlStorage implements StorageHandler {


    @Override
    public Account loadAccount(String name) {
        return null;
    }

    @Override
    public boolean hasRecord(String name) {
        return false;
    }

    @Override
    public void save(Account account) {

    }

}
