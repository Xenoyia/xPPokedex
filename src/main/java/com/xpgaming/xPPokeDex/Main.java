package com.xpgaming.xPPokeDex;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.text.Text;

@Plugin(id = Main.id, name = Main.name, version = "0.1", dependencies = {@Dependency(id = "pixelmon")})
public class Main {
    public static final String id = "xppokedex";
    public static final String name = "xP// PokeDex";
    private static final Logger log = LoggerFactory.getLogger(name);

    CommandSpec remaining = CommandSpec.builder()
            .description(Text.of("List Pokémon left to catch!"))
            .permission("xpgaming.pokedex.remaining")
            .executor(new Remaining())
            .build();

    CommandSpec count = CommandSpec.builder()
            .description(Text.of("Count number of Pokemon caught!"))
            .permission("xpgaming.pokedex.count")
            .executor(new Count())
            .build();

    CommandSpec pokedex = CommandSpec.builder()
            .description(Text.of("PokéDex things!"))
            .permission("xpgaming.pokedex")
            .child(count, "c", "count", "co")
            .child(remaining, "r", "remaining", "remain", "rem")
            .executor(new PokedexBase())
            .build();

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        log.info("Loaded v0.1!");
        Sponge.getCommandManager().register(this, pokedex, "pokedex", "pd", "dex");
    }
}
