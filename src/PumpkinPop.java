

//import java.util.ArrayList;

public class PumpkinPop{
	//holds the population of randomly generated DNA
	
	final int GENETYPES = 3;
	final int FACETYPES = 9;
	final int MOUTHTYPES = 23;
	final int EYETYPES = 10;
	double[][] pumpkinFitness; 
	PumpkinDNA[] population;
	String target;
	double mutationRate;
	int populationMax;
	int generation;
	boolean found;
	
	public PumpkinPop(double mutationRate, int popMax){
		
		
		pumpkinFitness = new double[GENETYPES][];
		pumpkinFitness[0] = new double[FACETYPES];
		pumpkinFitness[1] = new double[MOUTHTYPES];
		pumpkinFitness[2] = new double[EYETYPES];
		population = new PumpkinDNA[popMax];
		//populates randomly generated DNA
		for(int i = 0; i < popMax; i++){
			population[i] = new PumpkinDNA(GENETYPES);
		}
		//this.target = target;
		this.mutationRate = mutationRate;
		populationMax = popMax;
		generation = 0;
		//found = false;
	}
	//calculate fitness
	void fitness(){
		for(PumpkinDNA dna: population) 
			dna.setFitnessScoring(pumpkinFitness);
		
		for(PumpkinDNA dna: population) 
			dna.calculateFitness(pumpkinFitness);
	}
	//generate Mating Pool
	PumpkinDNA naturalSelection(double maxFitness){
		
		PumpkinDNA partner = null;
		double randomMaxFitness = 0;
		do{
			//pick a random number
			int index = (int)(Math.random()*population.length);
			
			//get the DNA found at that index
			partner = population[index];
			//if the fitness level the partner greater than or equal to 
			//a random number from 0 to max fitness keep it, if it is lower, then draw again
			//this is rejection sampling
			randomMaxFitness = Math.random()*maxFitness;
		}while(partner.fitness < randomMaxFitness);
		return partner;
	}
	//pick two parents and reproduce
	void reproduction(){
		//calculate the maximum fitness of any DNA
		double maxFitness = 0;
		for(PumpkinDNA dna: population){
			if(dna.fitness>maxFitness)
				maxFitness = dna.fitness;
		}	
		
		PumpkinDNA[] children = new PumpkinDNA[population.length];
		for(int i = 0; i < children.length; i++){
			//System.out.println(i);
			PumpkinDNA parentA = naturalSelection(maxFitness);
			PumpkinDNA parentB = naturalSelection(maxFitness);
			PumpkinDNA child = parentA.crossover(parentB);
			child.mutate(mutationRate);
			children[i] = child;
			
		}
		population = children;
		generation++;
	}
	public PumpkinDNA[] getPop(){
		return population;
	}
	
	
//	String getBestText(){
//		double best = 0.0;
//		double complete = 1.0;
//		
//		int index = 0;
//		for(int i = 0; i < population.length; i++){
//			if(population[i].fitness > best){
//				index = i;
//				best = population[i].fitness;
//			}
//				
//		}
//		//System.out.println(best + "here!!!!!!!!");
//		if(best >= complete)
//			found = true;
//		
//		return population[index].getText();
//	}
	//void print(){
	//	for(PumpkinDNA dna:population)
	//		System.out.println(dna.getText());
	//}

}

