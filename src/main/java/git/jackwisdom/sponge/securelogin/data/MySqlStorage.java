package git.jackwisdom.sponge.securelogin.data;

import com.google.common.base.Optional;

import javax.annotation.Nonnull;

public class MySqlStorage implements StorageHandler {
    @Override
    public void register(String name, String pwd, long time) {

    }

    @Override
    public Optional<Long> getLoginTime(String name) {
        return null;
    }

    @Nonnull
    @Override
    public void setLoginTime(String name, long time) {

    }

    @Override
    public Optional<String> getPassword(String name) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public boolean hasRecord(String name) {
        return false;
    }

    @Override
    public String getIp(String name) {
        return null;
    }

    @Override
    public int getAccounts(String ip) {
        return 0;
    }
}
