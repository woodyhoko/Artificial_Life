package project.s104501527;

public class resource {
	int data[][][] = new int[20][20][2];
	public resource(){
		for(int n = -1; n++ < 19;){
			for(int m = -1; m++ < 19;){
				data[n][m][0] = (int)(Math.random()*175)+75;
				data[n][m][1] = data[n][m][0];
			}
		}
	}
}
