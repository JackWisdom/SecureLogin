package git.jackwisdom.sponge.securelogin;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import git.jackwisdom.sponge.securelogin.data.StorageHandler;
import git.jackwisdom.sponge.securelogin.exception.NullPassword;
import org.spongepowered.api.entity.living.player.Player;

import java.util.HashSet;

public class API {
    private HashSet<String> loginedPlayer;
    @Inject
    private StorageHandler storageHandler;

    public API() {
        loginedPlayer = new HashSet<>();
    }

    public boolean isLogin(String s) {
        return loginedPlayer.contains(s);
    }

    public boolean isLogin(Player player) {
        return isLogin(player.getName());
    }

    public void register(String name, String pwd) {
        storageHandler.register(name, pwd, getTime());
    }

    public void register(String name) {
        register(name, "123456");
    }

    public void register(Player name, String pwd) {
        register(name.getName(), pwd);
    }

    public void register(Player name) {
        register(name.getName(), "123456");
    }

    public long getLastLogin(String name) {
        Optional<Long> time = storageHandler.getLoginTime(name);
        if (time.isPresent())
            return time.get();
        else
            return 0;
    }

    public long getLastLogin(Player name) {
        return getLastLogin(name.getName());
    }

    public void setLastLogin(String name, long time) {
        storageHandler.setLoginTime(name, time);
    }

    public void setLastLogin(Player name, long time) {
        storageHandler.setLoginTime(name.getName(), time);
    }

    public void updateLoginTime(String name) {
        setLastLogin(name, getTime());
    }

    public void updateLoginTime(Player name) {
        updateLoginTime(name.getName());
    }

    public String getPassword(String name) {
        Optional<String> pwd = storageHandler.getPassword(name);
        if (!pwd.isPresent()) {
            unRegister(name);
            throw new NullPassword();
        }
        return pwd.get();
    }

    public String getPassowrd(Player n) {
        return getPassword(n.getName());
    }

    //do not forget to update logintime while logining
    public boolean login(String name, String pwd_input) {
        String pwd_origin = getPassword(name);
        if (pwd_origin.equals(pwd_input))
            return true;
        else
            return false;
    }

    public boolean login(Player player, String pwd) {
        return login(player.getName(), pwd);
    }

    public void unRegister(String name) {
        storageHandler.delete(name);
    }

    public void unRegister(Player player) {
        unRegister(player.getName());
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public boolean isRegisted(String name) {
        return storageHandler.hasRecord(name);
    }

    public boolean isRegisted(Player player) {
        return isRegisted(player.getName());
    }
}
