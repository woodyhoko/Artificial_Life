# Artificial Life Simulation

An **evolutionary ecosystem simulator** where neural-network-brained creatures compete for survival, reproduce, and evolve across generations — with no hand-coded behaviours.

[![Demo — Competitive Mode](https://raw.githubusercontent.com/woodyhoko/Artificial_Life/main/AL_C.gif)](https://youtu.be/_E16iH62hlA)

🎥 **[Watch full demo — Competitive](https://youtu.be/_E16iH62hlA)** | **[Watch full demo — Survival](https://youtu.be/XocJwLX-YtI)**

---

## Simulation Design

### Environment

- A 2D playground with randomly distributed **grass patches**, each with its own unique regeneration rate
- Creatures consume grass to maintain energy; starving creatures die
- Creatures with higher energy **predate** on lower-energy creatures upon collision

### Creature Architecture

Each creature has:
- **3 antennas** (raycasts) as sensory inputs
- A **fully-connected neural network** that maps sensor readings to movement decisions
- A fixed energy budget that depletes each tick

### Evolved Behaviours

Given enough generations, well-performing neural networks learn to:
- 🏃 Avoid stronger enemies
- 🎯 Chase weaker prey
- 🌿 Linger in food-rich areas
- 🏜️ Avoid depleted desert zones

---

## Neuroevolution

- Each generation, **survivors reproduce** — offspring inherit parent weights with small mutations
- No gradient descent; selection pressure alone shapes the network over hundreds of generations
- The population self-organizes into predator/prey dynamics without explicit programming

---

## Demo

| Mode | Description |
|---|---|
| **Competitive** | Two species competing — watch territorial and predator-prey dynamics emerge |
| **Survival** | Single-species struggle for grass resources |

[![Demo — Survival Mode](https://raw.githubusercontent.com/woodyhoko/Artificial_Life/main/AL_S.gif)](https://youtu.be/XocJwLX-YtI)

