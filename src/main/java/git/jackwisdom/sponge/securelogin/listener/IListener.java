package git.jackwisdom.sponge.securelogin.listener;

import com.google.inject.Inject;
import git.jackwisdom.sponge.securelogin.API;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class IListener {
    @Inject
    API api;
    @Inject
    Logger logger;

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @First Player p) {
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event, @First Player player) {
    }

    @Listener
    public void onTst(GameStartedServerEvent event) {
        api.tst();
    }
}
