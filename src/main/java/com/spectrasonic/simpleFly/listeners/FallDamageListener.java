package com.spectrasonic.simpleFly.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;

/**
 * Listener to cancel fall damage for players with flight enabled.
 */
public class FallDamageListener implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player
                && event.getCause() == EntityDamageEvent.DamageCause.FALL
                && player.getAllowFlight()) {
            event.setCancelled(true);
        }
    }
}
