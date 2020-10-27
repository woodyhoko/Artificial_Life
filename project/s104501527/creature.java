package project.s104501527;

public class creature implements Comparable<creature>{
		public double xpos;
		public double ypos;
		public double angle;
		public double xspd;
		public double yspd;
		public double rad;
		public double power;
		public double lastpower;
		public double speed;
		public nn brain;
		public int struc[];
		public int ren;
		public int len;
		public int fen;
		public double rr;
		public double gg;
		public double bb;
		public int lifetime;
		public int gen;
		public double record[] = new double[1000];
		@Override
		public int compareTo(creature cr){
			double ccc = cr.lifetime;
			return (int) (this.lifetime - ccc);
		}
		
		public creature(int struc[]){
			this.xpos = 0;
			this.xpos = 0;
			this.xspd = 0;
			this.yspd = 0;
			this.speed = 0;
			this.rad = 10;
			this.power = Math.pow(rad, 2);
			this.lastpower = Math.pow(rad, 2);
			this.struc = struc;
			this.brain = new nn(struc);
			this.rr = Math.random();
			this.gg = Math.random();
			this.bb = Math.random();
			this.lifetime = 0;
		}
		public void getxyspd(){
			xspd = speed*Math.cos(angle);
			yspd = speed*Math.sin(angle);
		}
		public void think(double nc, double rc, double lc, double fc, double re, double le, double fe){
			lifetime++;
			brain.input(power/1400, lastpower/1400, nc, rc, lc, fc, re, le, fe);
			brain.cal(struc);
			if(brain.out[struc.length - 1][0] > 0.5){		//rotate or not
				angle += brain.out[struc.length - 1][2]*Math.PI/2-Math.PI/4;
			}else{
				brain.out[struc.length - 1][2] = 0.5;
			}
			speed = brain.out[struc.length - 1][1];
			if(brain.out[struc.length - 1][3] > brain.out[struc.length - 1][4]){
				power *= 0.8;
			}else{
				lastpower = power;
				power += nc*70;
			}

		}
		public void dna(nerual nmother[][], nerual nfather[][]){
			for(int n = -1; n++ < struc.length - 2;){
				for(int m = -1; m++ < struc[n+1] - 1;){
					double rannn = Math.random();
					if(rannn < 0.5){
						brain.narray[n][m] = new nerual(nmother[n][m]);
					}else if(rannn < 0.99){
						brain.narray[n][m] = new nerual(nfather[n][m]);
					}
				}
			}
		}
}
