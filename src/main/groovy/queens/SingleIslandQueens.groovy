package queens

import island_model.IslandEngine
import island_model.IslandProblemSpecification



def problem = new IslandProblemSpecification()
problem.nodes = 8
problem.instances = 11
problem.dataFileName = null
problem.populationClass = QueensIslandPopulation.getName()
problem.geneLength = 8
problem.populationPerNode = 16
problem.migrationInterval = 8
problem.migrationSize = 4
problem.crossoverPoints = 2
problem.maxGenerations = 2000
problem.crossoverProbability = 1.0
problem.mutationProbability = 0.8
problem.convergenceLimit = 0.0
problem.minOrMax = "MIN"
problem.doSeedModify = true
problem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                             1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                             10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                             12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]
RingTopology topology = new RingTopology()


String outFile = "./islandQueensSingle.csv"
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



