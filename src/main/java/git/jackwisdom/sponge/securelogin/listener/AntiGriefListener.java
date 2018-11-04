package git.jackwisdom.sponge.securelogin.listener;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.action.InteractEvent;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.entity.TargetEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.event.item.inventory.UseItemStackEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;

import java.util.HashSet;

public class AntiGriefListener {
    //监听时间阻止玩家使用指令、ETC
    HashSet<String> cmdWhitelist;
    boolean chatBeforeLogin = false;
    boolean fightBeforeLogin = false;

    //chat
    @Listener
    public void onPostCmd(SendCommandEvent event, @First Player player) {

    }

    @Listener
    public void onChat(MessageChannelEvent.Chat event, @First Player player) {
    }

    @Listener
    public void onMove(MoveEntityEvent event, @First Player player) {

    }

    //inv
    @Listener
    public void onDropItem(DropItemEvent event, @First Player player) {

    }

    @Listener
    public void onConsueItem(UseItemStackEvent event, @First Player player) {

    }

    @Listener
    public void onClickInv(ClickInventoryEvent event, @First Player player) {
    }

    //interact
    @Listener
    public void onInteractItem(InteractEvent e, @First Player player) {
    }

    //Entity
    @Listener
    public void onPlayerFight(DamageEntityEvent event, @First Player player) {

    }

    @Listener
    public void onEntityTarget(TargetEntityEvent event, @First Player player) {

    }
}
