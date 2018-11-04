package git.jackwisdom.sponge.securelogin.data;

import com.google.common.base.Optional;

import javax.annotation.Nonnull;

public interface StorageHandler {
    void register(String name, String pwd, long time);

    Optional<Long> getLoginTime(String name);

    @Nonnull
    void setLoginTime(String name, long time);

    Optional<String> getPassword(String name);

    void delete(String name);

    boolean hasRecord(String name);

    //WIP
    String getIp(String name);

    int getAccounts(String ip);
}
