package git.jackwisdom.sponge.securelogin;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import git.jackwisdom.sponge.securelogin.cfg.Config;
import git.jackwisdom.sponge.securelogin.cfg.ConfigFactory;
import git.jackwisdom.sponge.securelogin.data.FileStorage;
import git.jackwisdom.sponge.securelogin.data.StorageHandler;
import git.jackwisdom.sponge.securelogin.listener.AntiGriefListener;
import git.jackwisdom.sponge.securelogin.listener.IListener;
import org.slf4j.Logger;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "securelogin", name = "Secure Login", version = "0.01", description = "A auth plugin for offlne servers")
public class SecureLogin {
    @Inject
    private Logger logger;

    public API api;
    Injector injector;
    @Inject
    Injector super_injector;
    @Inject
    Config cfg;
    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        logger.info("Loading configurations");
        injector = super_injector.createChildInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(StorageHandler.class).to(FileStorage.class);
            }
        });
        logger.info("Loading fields for configuration");
        ConfigFactory.loadFields(cfg);

    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        logger.info("Regising listeners");
        regListeners();
    }

    @Listener
    public void onPostInit(GamePostInitializationEvent event) {

        logger.info("Interacting with other plugin");
        api = injector.getInstance(API.class);
    }

    @Listener
    public void onLoadCompete(GameLoadCompleteEvent event) {
        logger.info("Successfully loaded SecureLogin");
    }

    @Listener
    public void onServerStarting(GameStartingServerEvent event) {
        logger.info("Registering Commands");
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Welcome to use SecureLogin");
    }

    //getters
    public API getApi() {
        return api;
    }

    //cmd&listener
    private void buildCmds() {
        CommandSpec cmd = CommandSpec.builder().build();
    }

    @Inject
    private EventManager events;
    private void regListeners() {
        events.registerListeners(this, injector.getInstance(AntiGriefListener.class));
        events.registerListeners(this, injector.getInstance(IListener.class));
    }
}
