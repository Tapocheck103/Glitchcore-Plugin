package com.tapocheck103.glitchcorePlugin.events;

import com.tapocheck103.glitchcorePlugin.anomalies.ParticleGlitchAnomaly;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class AnomalyListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        new ParticleGlitchAnomaly().trigger(event.getPlayer(), event.getBlock());
    }
}
