package queens

import island_model.IslandEngine
import island_model.IslandProblemSpecification

def problem = new IslandProblemSpecification()

boolean doSeedModify = true
problem.instances = 11
problem.dataFileName = null
problem.populationClass = QueensIslandPopulation.getName()
problem.populationPerNode = 8
problem.migrationSize = 2
problem.crossoverPoints = 2
problem.maxGenerations = 4000
problem.minOrMax = "MIN"
problem.doSeedModify = doSeedModify
// seeds chosen such that 100 instances can be run consecutively without
// any seed being repeated, assuming seeds incremented by 2 for each new instance
problem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                 1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                 10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                 12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]

RingTopology topology = new RingTopology()

  int gl = 32
  problem.geneLength = gl
  problem.convergenceLimit = 0.0
  for (n in [4, 6, 8, 10, 12, 14, 16]) {
    problem.nodes = n
    for (cp in [1.0, 0.9, 0.8]) {
      problem.crossoverProbability = cp
      for (mp in [0.9, 0.8]) {
        problem.mutationProbability = mp
        for (mi in [8, 12, 16]) {
          problem.migrationInterval = mi

          String outFile = "./island${gl}.csv"
          def fw = new FileWriter(outFile, true)
          def bw = new BufferedWriter(fw)
          def printWriter = new PrintWriter(bw)

          def islandEngine = new IslandEngine(problemSpecification: problem,
              topology: topology,
              instances: problem.instances,
              doSeedModify: problem.doSeedModify,
              nodes: problem.nodes,
              printWriter: printWriter)
          islandEngine.invoke()
        }
      }
    }
  }







