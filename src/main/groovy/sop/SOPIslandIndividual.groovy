package sop

import island_model.IslandIndividual

class SOPIslandIndividual implements IslandIndividual{
  int fitness
  List <Integer> route = []
  int geneLength
  List distances
  Map precedences

  SOPIslandIndividual(int geneLength, Random rng,
                      List distances, Map precedences){
    this.distances = distances
    this.precedences = precedences
    this.geneLength = geneLength
    for ( i in 0 .. geneLength) route[i] = 0
    route[1] = 1
    route[geneLength] = geneLength
    int cities = geneLength
    int place = rng.nextInt(cities) + 1   //1.. cities
    for ( i in 2 ..< cities ) {
      while ((place == 1) || (place == geneLength) ||
          (route.contains(place))) place = rng.nextInt(cities) + 1
      route[i] = place
    }
    route[cities] = cities
    evaluateFitness(this.distances, this.precedences)
//    println "Route: $route, $geneLength $fitness"

  }

  SOPIslandIndividual(int geneLength,
                      List distances, Map precedences ){
    this.distances = distances
    this.precedences = precedences
    this.geneLength = geneLength
    route = []
    for ( i in 0 .. geneLength ) route[i] = 0  // just initialise, will be overwritten
  }

//  @Override
  void evaluateFitness(List distances, Map precedences) {
    // ensure precedences are enforced
    boolean finished = false
    while (!finished){
      boolean swapped = false
      precedences.each{ Integer after, List befores ->
        int e = 2
        while (route[e] != after) e = e + 1  // every node must be there so no size check
        int b = geneLength - 1 // last city is always fixed
        while ((b > e) && (! befores.contains(route[b]))) b = b - 1
        if ( b > e){
          route.swap(b, e)
          swapped = true
//        println "b: $b, e: $e, $iRoute"
        }
      }
      finished = ! swapped

    } //finished
    // now determine the fitness value
//    println "reordered: $route"
    fitness = 0
//      println "EF: ${route} "
    for ( int i in 2 .. geneLength){
//      println "EF: $i, ${i-1}, ${route} "
      fitness = fitness + distances[route[i-1]] [route[i]]
    }

  }
/**
 *
 * calculates the fitness value(s) of an individual
 *
 * @param evaluateData any data used to determine the fitness of an individual
 */

  @Override
  void evaluateFitness(List evaluateData) {
    // needs to be modified for this application
  }

  @Override
  void mutate(Random rng) {
    int place1 = rng.nextInt(geneLength - 3) + 2  //2..genelength-1
    int place2 = rng.nextInt(geneLength - 3) + 2
    while (place1 == place2) place2 = rng.nextInt(geneLength - 3) + 2
    route.swap(place1, place2)
  }

  @Override
  List <Integer> getSolution() {
    return route
  }

  @Override
  BigDecimal getFitness() {
    return fitness
  }

  String toString(){
    return "$route : $fitness "
  }
}
