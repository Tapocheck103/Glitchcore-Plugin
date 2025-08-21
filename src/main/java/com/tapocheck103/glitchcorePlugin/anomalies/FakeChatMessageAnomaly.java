package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.Bukkit;

public class FakeChatMessageAnomaly {
    public void trigger(String fakeName, String message) {
        String fakeMessage = "§7<" + fakeName + ">§r " + message;
        Bukkit.broadcastMessage(fakeMessage);
    }
}
