# Artificial Life — Neuroevolution Ecosystem Simulator

*Neural-network creatures emerge predator/prey dynamics, foraging strategies, and territorial behaviour — with no hand-coded rules.*

[![Demo — Competitive Mode](https://raw.githubusercontent.com/woodyhoko/Artificial_Life/main/AL_C.gif)](https://youtu.be/_E16iH62hlA)

🎥 **[Competitive demo](https://youtu.be/_E16iH62hlA)** | **[Survival demo](https://youtu.be/XocJwLX-YtI)**

---

## 1. What is artificial life?

Artificial life (ALife) studies *life as it could be* rather than life as it is — asking which properties associated with living systems (self-organization, homeostasis, adaptation, collective behaviour) can arise from formally specified local rules without being explicitly programmed (Langton 1989). This project is in the tradition of **agent-based ALife** models: each agent follows simple reactive rules, and complex, life-like global behaviour emerges from their interaction.

The closest antecedents are **Braitenberg vehicles** (Braitenberg 1984) — simple sensorimotor agents whose wiring patterns produce seemingly intentional behaviour — and the **animat** paradigm (Wilson 1991) — agents that must learn to survive in simulated environments. The key departure here is that agent controllers are evolved, not designed.

---

## 2. Environment

The simulation runs on a 2D toroidal plane populated with:

- **Grass patches** — distributed heterogeneously, each with an individual regrowth rate sampled from an exponential distribution. This creates a spatial resource landscape with "oases" and "deserts."
- **Creatures** — autonomous agents with limited sensory range, a finite energy budget, and a neural-network controller.

Energy budget dynamics:

```
energy[t+1] = energy[t]
             − metabolism_cost             # fixed depletion per tick
             + grass_consumed              # if standing on a patch
             − 0                          # if predated
```

A creature whose energy reaches zero is removed from the simulation. A creature that reaches the reproduction threshold creates an offspring (with mutated weights) and loses half its energy.

---

## 3. Creature design

### 3.1 Sensors

Each creature has **three antenna raycasts** emanating from its body at angular offsets (e.g. 0°, ±45°). Each raycast returns:

- **Distance to the nearest grass patch** (normalized)
- **Distance to the nearest other creature** + its relative energy level

The raw sensor vector has **6 values**: 3 grass distances × 1 + 3 creature proximity signals × 1.

### 3.2 Neural network controller

The sensory vector is fed through a **fixed-topology fully-connected network**:

```
Input (6) → Hidden (8, ReLU) → Output (2, tanh)
         output = [turn_angle, speed_multiplier]
```

The network is evaluated every simulation tick. Its weights are the entire genome — there are no hand-coded behaviour trees or state machines. Behaviours (chasing, fleeing, foraging) must be discovered by the evolutionary process.

### 3.3 Predation

When two creatures collide, the one with higher energy **consumes** the other, gaining energy equal to a fraction of the prey's energy budget. This implements a simple but effective energetic dominance rule — stronger creatures grow stronger, weaker ones are eliminated — creating *de facto* predator-prey dynamics without explicit type tagging.

---

## 4. Neuroevolution

The evolutionary algorithm is a **generational (μ, λ) strategy**:

1. **Evaluate:** run the simulation until the population stabilizes or a time limit is reached. Each creature accumulates a fitness score (energy gained − energy spent).
2. **Select:** the top *k* % of creatures by fitness survive to reproduce.
3. **Reproduce:** each survivor produces offspring by cloning its weight vector and applying Gaussian mutation:
   ```
   wᵢ ← wᵢ + 𝒩(0, σ)     with probability p_mutate
   ```
4. **Replace:** the new generation fills the population.

There is **no gradient signal** — the fitness landscape is the environment itself. The process is analogous to natural selection operating on a heritable genotype (the weights) via a phenotypic fitness proxy (survival and reproduction success).

### 4.1 Emergent strategies

Over hundreds of generations the population self-organizes around stable ecological strategies:

| Emergent behaviour | Mechanism |
|---|---|
| **Grazing** | Network learns to steer toward grass-proximity signals |
| **Pursuit** | Asymmetric energy means stronger creatures approach weaker ones |
| **Evasion** | Lighter creatures learn to turn away from proximity signals of stronger ones |
| **Territorial loitering** | Creatures with high-regrowth patches nearby learn to stay |

These strategies are not programmed — they are attractors of the evolutionary dynamics given the fitness landscape.

---

## 5. Simulation modes

| Mode | Description |
|---|---|
| **Competitive** | Two genetically isolated species; watch inter-species territorial and predation dynamics |
| **Survival** | Single species; intra-species competition for finite grass resources |

[![Demo — Survival Mode](https://raw.githubusercontent.com/woodyhoko/Artificial_Life/main/AL_S.gif)](https://youtu.be/XocJwLX-YtI)

---

## 6. Theoretical connections

This simulation touches several branches of complexity science:

- **Red Queen dynamics** — species co-evolve in an arms race, each adapting to the other's adaptations (Van Valen 1973)
- **Lotka-Volterra** — the predator-prey oscillations qualitatively match the classical ecological model, emerging here from first principles
- **Adaptive landscapes** (Wright 1932) — the weight space forms a fitness landscape that the evolutionary search navigates
- **Self-organized criticality** — population levels tend toward an "edge of chaos" attractor rather than extinction or saturation

---

## 7. References

1. C. G. Langton (ed.). *Artificial Life.* Addison-Wesley, 1989.
2. V. Braitenberg. *Vehicles: Experiments in Synthetic Psychology.* MIT Press, 1984.
3. S. W. Wilson. "The Animat Path to AI." *From Animals to Animats*, MIT Press, 1991.
4. L. Van Valen. "A New Evolutionary Law." *Evolutionary Theory*, 1:1–30, 1973.
5. S. Wright. "The roles of mutation, inbreeding, crossbreeding, and selection in evolution." *Proc. 6th Int. Congress of Genetics*, 1:356–366, 1932.
