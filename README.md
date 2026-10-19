# Valeria (NeoForge 1.21.1, Java 21)

A peaceful pastel dimension of floating islands. Requires **NeoForge 21.1.x** and **Curios API 9.x (1.21.1)**.

## Getting the .jar (no local setup)
1. Create a free GitHub repo and upload everything in this folder (keep the `.github` folder).
2. Open the **Actions** tab -> "Build Valeria jar" (it also runs on every push) -> wait ~3 minutes.
3. Download the `valeria-jar` artifact, unzip it, and put `valeria-1.0.0.jar` in your `mods` folder next to the Curios jar.

(If you do have Gradle 8.10+ and JDK 21: `gradle build` -> `build/libs/valeria-1.0.0.jar`.)

## Using it
- Enter the dimension: `/execute in valeria:valeria run spreadplayers 0 0 0 200 false @s`
  (finds an island surface for you; plain `/tp` can drop you in the void).
- Creative tab "Valeria" has every block, item and spawn egg.
- Cloud's Jewels: found in the chest hidden in a sealed vault under the floor of each Celestial Shrine
  (dig down from the centre pedestal). Put it in the Curios **Neck** slot -> hidden Resistance IV (no icon, no particles).
- Cloud Ray: feed Aetherium Crystals (1/3 chance each), then right-click with an empty hand to ride.
  Move forward + look up/down to climb/descend.
- Glimmer Stag: drops Shimmering Velvet; breed with sweet berries.

## Not implemented / approximations
- Dual sun/moons and a custom gradient sky renderer: the sky/fog colours are pastel via biome colours, vanilla sun/moon.
- "Light level 7 minimum": approximated with `ambient_light` in the dimension type (brighter nights, not true block light).
- Waterfalls use vanilla water springs; they do not freeze or turn to mist.
- No portal: use the command above.
