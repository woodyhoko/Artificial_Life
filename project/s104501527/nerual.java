package project.s104501527;

import java.util.Arrays;

public class nerual {
	public double w[];
	public nerual(nerual cn){
		this.w = Arrays.copyOf(cn.w, cn.w.length);
	}
	public nerual(int nn){
		w = new double[nn];
		for(int n = -1; n++ < nn - 1;){
			w[n] = Math.random()*2-1;
		}
	}
	public double cal(double in[]){
		double sum = 0;
		for(int n = -1; n++ < w.length - 1;){
			sum += w[n]*in[n];
		}
		return 1/(1+Math.exp(-sum));
	}
}
