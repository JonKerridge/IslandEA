package sop

import island_model.IslandEngine
import island_model.IslandProblemSpecification
import tsp.RingTopology

def sopSpecification = new IslandProblemSpecification()
sopSpecification.nodes = 4
sopSpecification.instances = 11
sopSpecification.dataFileName = "./ESC07.sop" //
sopSpecification.populationClass = SOPIslandPopulation.getName()
sopSpecification.geneLength = 9
sopSpecification.populationPerNode = 16
sopSpecification.migrationInterval = 16
sopSpecification.migrationSize = 4
sopSpecification.crossoverPoints = 2
sopSpecification.maxGenerations = 4000
sopSpecification.crossoverProbability = 1.0
sopSpecification.mutationProbability = 0.1
sopSpecification.convergenceLimit = 2130
sopSpecification.minOrMax = "MIN"
sopSpecification.doSeedModify = true
sopSpecification.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                          1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                          10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                          12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]
RingTopology topology = new RingTopology()


String outFile = "./IslandSOP7.csv"
def fw = new FileWriter(outFile, true)
def bw = new BufferedWriter(fw)
def printWriter = new PrintWriter(bw)

def islandEngine = new IslandEngine(
    problemSpecification: sopSpecification,
    topology: topology,
    instances: sopSpecification.instances,
    doSeedModify: sopSpecification.doSeedModify,
    nodes: sopSpecification.nodes,
    printWriter: printWriter)
islandEngine.invoke()



