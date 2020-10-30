package project.s104501527;

public class nn {
	public double out[][];
	public nerual narray[][];
	public nn(int struc[]){
		out = new double[struc.length][];
		out[0] = new double[struc[0]];
		narray = new nerual[struc.length - 1][];
		for(int n = -1; n++ < struc.length - 2;){
			out[n+1] = new double[struc[n+1]];
			narray[n] = new nerual[struc[n+1]];
			for(int m = -1; m++ < struc[n+1] - 1;){
				narray[n][m] = new nerual(struc[n]);
			}
		}
	}
	public void input(double npow, double lpow, double a, double b, double c, double d, double e, double f, double g){
		out[0][0] = npow;
		out[0][1] = lpow;
		out[0][2] = a;
		out[0][3] = b;
		out[0][4] = c;
		out[0][5] = d;
		out[0][6] = e;
		out[0][7] = f;
		out[0][8] = g;
		//System.out.printf("%f,%f,%f,%f,%f,%f\n",npow,lpow,a,b,c,d);
	}
	public void cal(int struc[]){
		for(int n = -1; n++ < struc.length - 2;){
			for(int m = -1; m++ < struc[n+1] - 1;){
				out[n+1][m] = narray[n][m].cal(out[n]);
			}
		}
	}
}
