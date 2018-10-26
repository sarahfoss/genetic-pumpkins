
public class PumpkinDNA {
	int[] genes;
	double fitness;
	int faces = 9;
	int mouthes = 23;
	int eyes =10;
	int score;
	
	PumpkinDNA(int geneLength){
		genes = new int[geneLength];
		fitness = 0;
		score = 0;
		genes[0] = (int)(Math.random()*faces);
		genes[1] = (int)(Math.random()*mouthes);
		genes[2] = (int)(Math.random()*eyes);
		
	}
	void setFitnessScoring(double[][] pFitness) {
		for(int i = 0; i < genes.length; i++) {
			pFitness[i][genes[i]] += score; 
		}
	}
	
	void calculateFitness(double[][] pFitness){
		int sc = 0;
		for(int i = 0; i < genes.length; i++){
			sc += pFitness[i][genes[i]];
		}
		
	
		//gives a percentage fitness score
		fitness = (double)sc/genes.length;
		//make the fitness exponential to improve score
		fitness = fitness != 0? Math.pow(fitness, 3) : 0.00000000001;
		
		
	}
	
	PumpkinDNA crossover(PumpkinDNA parentB){
		PumpkinDNA child = new PumpkinDNA(genes.length);
		int mid = (int)(Math.random()*genes.length);
		for(int i = 0; i < genes.length; i++){
			if(i <= mid)
				child.genes[i]=genes[i];
			else
				child.genes[i]=parentB.genes[i];
		}
		return child;
	}
	
	void mutate(double mutationRate){
		if(Math.random()<mutationRate)
			genes[0] = (int)(Math.random()*faces);
		if(Math.random()<mutationRate)
			genes[1] = (int)(Math.random()*mouthes);
		if(Math.random()<mutationRate)
			genes[2] = (int)(Math.random()*eyes);
		
	}
	int[] getGenes() {
		return genes;
	}

}
