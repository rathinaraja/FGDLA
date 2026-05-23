# FGDLA: Fine-Grained Data Locality-Aware MapReduce Scheduling

This repository contains Java-based research implementations for **Fine-Grained Data Locality-Aware (FGDLA) MapReduce Scheduling**. The main research focus is to improve Hadoop MapReduce scheduling by combining fair scheduling, block placement awareness, data locality optimization, and combiner-based intermediate data reduction.

The repository includes five related research works, each exploring a different scheduling or optimization strategy:

```text
FGDLA/
├── Fair_Blck_FGDLA_FCFS/
├── Fair_Blck_FGDLA_FAIR/
├── Fair_Blck_FGDLA_RR/
├── Fair_Blck_FGDLA_Combiner/
├── Fair_Blck_FGDLA_MLPNC/
└── README.md
```

---

## Table of Contents

- [Overview](#overview)
- [Acronyms and Folder Meanings](#acronyms-and-folder-meanings)
- [Research Motivation](#research-motivation)
- [Repository Structure](#repository-structure)
- [Research Works](#research-works)
  - [1. Fair_Blck_FGDLA_FCFS](#1-fair_blck_fgdla_fcfs)
  - [2. Fair_Blck_FGDLA_FAIR](#2-fair_blck_fgdla_fair)
  - [3. Fair_Blck_FGDLA_RR](#3-fair_blck_fgdla_rr)
  - [4. Fair_Blck_FGDLA_Combiner](#4-fair_blck_fgdla_combiner)
  - [5. Fair_Blck_FGDLA_MLPNC](#5-fair_blck_fgdla_mlpnc)
- [Core Concepts](#core-concepts)
- [Expected Workflow](#expected-workflow)
- [Possible Evaluation Metrics](#possible-evaluation-metrics)
- [Recommended Study Order](#recommended-study-order)
- [How to Run the Implementations](#how-to-run-the-implementations)
- [Expected Research Contribution](#expected-research-contribution)
- [Future Improvements](#future-improvements)
- [License](#license)

---

## Overview

Hadoop MapReduce is a distributed computing framework used to process large-scale datasets across multiple machines. A major performance factor in MapReduce is **data locality**, which refers to executing computation as close as possible to the physical location of the input data.

When map tasks are scheduled on nodes that already contain the required input data blocks, the system can reduce network traffic, improve execution speed, and increase cluster efficiency. However, maintaining data locality while also ensuring fairness among jobs is difficult, especially in multi-user and dynamic cluster environments.

This repository explores **Fine-Grained Data Locality-Aware MapReduce Scheduling (FGDLA)**. The goal is to make scheduling decisions that consider:

- Fairness among jobs
- Block placement
- Node-level data locality
- Map and reduce task coordination
- Intermediate data transfer cost
- Scheduling policy differences
- Combiner-based optimization
- Multi-level per-node aggregation

Each folder represents a different research variation of the FGDLA scheduling strategy.

---

## Acronyms and Folder Meanings

| Term | Meaning |
|---|---|
| `Fair` | Fair scheduling strategy, aiming to balance resource allocation among jobs |
| `Blck` | Block placement, referring to placement and use of input data blocks in Hadoop |
| `FGDLA` | Fine-Grained Data Locality-Aware MapReduce Scheduling |
| `FCFS` | First Come First Serve scheduling |
| `FAIR` | Fair Scheduler-based scheduling |
| `RR` | Round Robin scheduling |
| `Combiner` | Plain combiner placed between map and reduce tasks |
| `MLPNC` | Multi-Level Per-Node Combiner used during map and reduce processing |

---

## Research Motivation

In standard MapReduce execution, performance can be affected by several problems:

- Map tasks may be scheduled on nodes far from their input data.
- Poor block placement can increase cross-node data transfer.
- Default scheduling may not consider fine-grained locality.
- Fairness and locality can conflict with each other.
- Reduce tasks can receive large volumes of intermediate data.
- Network overhead can become a bottleneck.
- Combiner usage may be limited or not optimized per node.
- Multi-job environments require balanced resource allocation.

The FGDLA research direction attempts to improve MapReduce performance by making scheduling and block placement decisions more locality-aware and fairness-aware.

The repository compares different scheduling strategies and combiner-based approaches to identify how each method affects execution time, locality, fairness, and network cost.

---

## Repository Structure

```text
FGDLA/
├── Fair_Blck_FGDLA_FCFS/
├── Fair_Blck_FGDLA_FAIR/
├── Fair_Blck_FGDLA_RR/
├── Fair_Blck_FGDLA_Combiner/
├── Fair_Blck_FGDLA_MLPNC/
└── README.md
```

Each folder corresponds to a separate research implementation or experiment.

---

# Research Works

## 1. Fair_Blck_FGDLA_FCFS

Folder:

```text
Fair_Blck_FGDLA_FCFS/
```

This work combines fair block placement and fine-grained data locality-aware scheduling with a **First Come First Serve (FCFS)** scheduling strategy.

FCFS is one of the simplest scheduling methods. Jobs are processed in the order in which they arrive. Although FCFS is easy to implement, it may not always provide optimal fairness, locality, or resource utilization in distributed environments.

### Objective

The goal of this work is to evaluate how fine-grained data locality and block placement awareness affect MapReduce performance when jobs are scheduled using FCFS.

### Key Ideas

- Jobs are executed in arrival order.
- Data locality is considered during task placement.
- Block placement is used to reduce unnecessary data movement.
- FCFS acts as a baseline scheduling policy for comparison.

### Expected Contribution

This work provides a baseline implementation for studying the effect of FGDLA under a simple scheduling policy.

### Possible Evaluation Focus

- Job execution time
- Waiting time
- Local map task ratio
- Remote map task ratio
- Network transfer cost
- Comparison with non-locality-aware FCFS scheduling

---

## 2. Fair_Blck_FGDLA_FAIR

Folder:

```text
Fair_Blck_FGDLA_FAIR/
```

This work integrates fine-grained data locality-aware scheduling with a **Fair Scheduler** approach.

The Fair Scheduler attempts to distribute cluster resources fairly among multiple jobs or users. In a shared Hadoop cluster, fairness is important because multiple jobs may compete for limited compute resources.

### Objective

The goal of this work is to improve MapReduce scheduling by combining fairness, block placement, and data locality awareness.

### Key Ideas

- Resources are allocated fairly across jobs or queues.
- Scheduling decisions consider the location of input data blocks.
- Data locality is improved without sacrificing fairness.
- The scheduler attempts to reduce starvation and resource imbalance.

### Expected Contribution

This work demonstrates how fair scheduling can be improved by adding fine-grained block placement and locality-aware task assignment.

### Possible Evaluation Focus

- Fairness index
- Job completion time
- Average waiting time
- Cluster utilization
- Data locality percentage
- Resource allocation balance
- Number of starved jobs

---

## 3. Fair_Blck_FGDLA_RR

Folder:

```text
Fair_Blck_FGDLA_RR/
```

This work applies fine-grained data locality-aware scheduling with a **Round Robin (RR)** scheduling strategy.

Round Robin scheduling assigns resources cyclically among jobs or queues. It is simple and can prevent starvation, but it may not always make the best locality-aware decisions unless additional logic is included.

### Objective

The goal of this work is to evaluate how Round Robin scheduling performs when enhanced with fair block placement and fine-grained data locality awareness.

### Key Ideas

- Jobs or tasks are scheduled in cyclic order.
- Each job receives scheduling opportunities in turn.
- Fine-grained locality is used to select suitable nodes.
- Block placement awareness is used to reduce remote reads.

### Expected Contribution

This work provides a locality-aware Round Robin scheduling strategy and helps compare RR against FCFS and Fair Scheduler variants.

### Possible Evaluation Focus

- Turn-based resource allocation
- Job starvation reduction
- Task locality percentage
- Scheduling overhead
- Load distribution
- Makespan
- Comparison with FCFS and Fair Scheduler versions

---

## 4. Fair_Blck_FGDLA_Combiner

Folder:

```text
Fair_Blck_FGDLA_Combiner/
```

This work introduces a **plain combiner** between map and reduce tasks in the FGDLA scheduling framework.

In Hadoop MapReduce, a combiner is an optional mini-reducer that runs after the map phase and before the reduce phase. It reduces the volume of intermediate data transferred across the network.

### Objective

The goal of this work is to improve MapReduce performance by reducing intermediate data transfer using a combiner while maintaining fair block placement and fine-grained locality-aware scheduling.

### Key Ideas

- A plain combiner is used between map and reduce tasks.
- Intermediate key-value pairs are partially aggregated before shuffle.
- Network traffic between map and reduce phases is reduced.
- Locality-aware map scheduling is combined with intermediate data reduction.

### Expected Contribution

This work evaluates whether adding a combiner improves execution performance under FGDLA-based scheduling.

### Possible Evaluation Focus

- Intermediate data size
- Shuffle data volume
- Network overhead
- Reduce task processing time
- Job completion time
- Improvement over FGDLA without combiner
- Combiner effectiveness ratio

### Example Use Case

For word count or aggregation-heavy workloads, the combiner can reduce repeated intermediate values before they are sent to reducers.

Example:

```text
Map output before combiner:
(word, 1), (word, 1), (word, 1)

Combiner output:
(word, 3)
```

This reduces the amount of data transferred during shuffle.

---

## 5. Fair_Blck_FGDLA_MLPNC

Folder:

```text
Fair_Blck_FGDLA_MLPNC/
```

This work extends the combiner-based approach by introducing **Multi-Level Per-Node Combiner (MLPNC)** during map and reduce processing.

Unlike a plain combiner, which may operate only at a basic intermediate stage, MLPNC aims to perform more structured aggregation at multiple levels within each node before the reduce phase. This can reduce shuffle cost further and improve node-level efficiency.

### Objective

The goal of this work is to improve MapReduce performance by using multi-level per-node combining along with fair block placement and fine-grained data locality-aware scheduling.

### Key Ideas

- Combiner logic is applied at multiple levels.
- Intermediate data is aggregated at the node level.
- Per-node aggregation reduces duplicate key-value transfers.
- Fine-grained scheduling improves data locality.
- Multi-level aggregation improves shuffle and reduce efficiency.

### Expected Contribution

This work proposes a stronger optimization than plain combiner-based scheduling by reducing intermediate data transfer more aggressively.

### Possible Evaluation Focus

- Intermediate data reduction rate
- Per-node aggregation efficiency
- Shuffle phase reduction
- Reducer workload reduction
- Job completion time
- Network overhead reduction
- Scalability under large datasets
- Comparison with plain combiner approach

### Difference Between Plain Combiner and MLPNC

| Feature | Plain Combiner | MLPNC |
|---|---|---|
| Aggregation level | Basic map-output level | Multi-level per-node aggregation |
| Optimization scope | Local to mapper output | Node-level and multi-stage |
| Data reduction | Moderate | Potentially higher |
| Shuffle reduction | Partial | Stronger |
| Scheduling integration | Simple | More advanced |
| Expected performance | Improved over no combiner | Improved over plain combiner |

---

## Core Concepts

This repository is related to the following concepts:

- Hadoop MapReduce
- Fair scheduling
- First Come First Serve scheduling
- Round Robin scheduling
- Block placement
- Fine-grained data locality
- Data-local task assignment
- Combiner optimization
- Multi-level per-node combining
- Intermediate data reduction
- Shuffle phase optimization
- Distributed computing
- Cluster resource management
- Big data processing

---

## Expected Workflow

A general FGDLA-based MapReduce workflow can be represented as:

```text
Input Dataset
      ↓
HDFS Block Placement
      ↓
MapReduce Job Submission
      ↓
Scheduling Policy Selection
      ↓
Fine-Grained Data Locality-Aware Task Assignment
      ↓
Map Task Execution
      ↓
Combiner / Multi-Level Per-Node Combiner
      ↓
Shuffle and Sort
      ↓
Reduce Task Execution
      ↓
Final Output
      ↓
Performance Evaluation
```

---

## Possible Evaluation Metrics

The following metrics can be used to evaluate and compare the research works:

| Metric | Description |
|---|---|
| Job Completion Time | Total time required to finish a MapReduce job |
| Makespan | Total time required to complete all submitted jobs |
| Waiting Time | Time a job waits before execution |
| Data Locality Rate | Percentage of tasks executed on nodes containing required input data |
| Remote Data Access | Number or percentage of tasks reading data from remote nodes |
| Network Overhead | Amount of data transferred across the cluster |
| Shuffle Data Volume | Intermediate data transferred from mappers to reducers |
| Combiner Reduction Ratio | Reduction in intermediate records due to combiner use |
| Fairness Index | Degree of fairness in resource allocation among jobs |
| Resource Utilization | CPU, memory, disk, and network usage across cluster nodes |
| Load Balance | Distribution of tasks across worker nodes |
| Scheduler Overhead | Time consumed by scheduling decisions |

---

## Comparative Research View

The five implementations can be compared as follows:

| Research Work | Scheduling Strategy | Block Placement | Data Locality | Combiner Strategy |
|---|---|---|---|---|
| Fair_Blck_FGDLA_FCFS | First Come First Serve | Yes | Fine-grained | No |
| Fair_Blck_FGDLA_FAIR | Fair Scheduler | Yes | Fine-grained | No |
| Fair_Blck_FGDLA_RR | Round Robin | Yes | Fine-grained | No |
| Fair_Blck_FGDLA_Combiner | Fair / locality-aware scheduling | Yes | Fine-grained | Plain combiner |
| Fair_Blck_FGDLA_MLPNC | Fair / locality-aware scheduling | Yes | Fine-grained | Multi-level per-node combiner |

---

## Recommended Study Order

Study the folders in this order:

```text
1. Fair_Blck_FGDLA_FCFS
2. Fair_Blck_FGDLA_FAIR
3. Fair_Blck_FGDLA_RR
4. Fair_Blck_FGDLA_Combiner
5. Fair_Blck_FGDLA_MLPNC
```

This order allows the reader to first understand the baseline scheduling policies and then move toward combiner-based optimization.

---

## How to Run the Implementations

The exact command may vary depending on how each folder is implemented. A general Hadoop execution pattern is:

```bash
hadoop jar <jar-file-name>.jar <MainClassName> <input_path> <output_path>
```

Example:

```bash
hadoop jar FGDLA.jar MainDriver /input/data /output/fgdla_result
```

If output already exists, remove it before rerunning:

```bash
hdfs dfs -rm -r /output/fgdla_result
```

View output:

```bash
hdfs dfs -cat /output/fgdla_result/part-r-00000
```

Check input files:

```bash
hdfs dfs -ls /input/data
```

---

## Expected Research Contribution

This repository contributes to the study of MapReduce scheduling by organizing multiple locality-aware scheduling strategies under one research theme.

The expected contribution includes:

- A fair block placement-aware scheduling framework
- Comparison of FCFS, Fair Scheduler, and Round Robin strategies
- Fine-grained data locality-aware task assignment
- Plain combiner-based intermediate data reduction
- Multi-level per-node combiner strategy for improved shuffle optimization
- Experimental foundation for evaluating scheduling fairness, locality, and performance

---

## Notes

- Each folder represents a separate research implementation.
- The code is expected to be Java-based for Hadoop MapReduce.
- Some folder names use abbreviated terms such as `Blck`, `FGDLA`, and `MLPNC`; their meanings are explained above.
- If experimental results are available, include result tables, graphs, logs, or screenshots inside each folder.
- Each work should ideally include its own input files, output examples, execution commands, and experimental notes.
- Avoid leaving code undocumented. Scheduling research code is difficult to understand without algorithm explanation and sample execution steps.

---

## Future Improvements

Possible future improvements include:

- Add algorithm pseudocode for each scheduling method
- Add architecture diagrams for FGDLA
- Add flowcharts for FCFS, FAIR, RR, Combiner, and MLPNC workflows
- Include sample input workloads
- Include expected outputs
- Add experimental result tables
- Add performance comparison graphs
- Add comments inside Java code
- Add Maven or Gradle build support
- Add Hadoop version information
- Add cluster configuration details
- Compare against Hadoop FIFO, Capacity Scheduler, and default Fair Scheduler
- Extend the method to Apache Spark or YARN-level scheduling
- Add reproducibility instructions for experiments

---

## License

This repository is intended for academic and research purposes.

You may use, modify, and extend the code with appropriate attribution.
