# Configurable Mining Delay

This is a fully client-side mod that allows you to change the delay between mining and placing blocks.

The mining delay can be changed from the default 5 ticks to 0-20, using the command `/miningdelay delay <ticks>`, and the placement delay can be changed from the default 4 ticks to 0-20, using the command `/miningdelay placement_delay <ticks>`.

By default, delay modification is disabled when connected to a server, as most servers with anti-cheat enabled will ban you for doing it. Using the command `/miningdelay enabledOnServers true` will enable the modified mining delay on servers, and `/miningdelay enabledOnServers false` will disable it again.

Changes you make via commands are saved to `miningdelay.json` located in the config folder. Note that manually editing the file while in-game won't apply the changes in real-time like the commands do.