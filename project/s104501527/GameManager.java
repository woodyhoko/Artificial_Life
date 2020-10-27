package project.s104501527;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* This class manage the object in game.
*/

public class GameManager {
    private long lastUpdateTime = 0;
    private GraphicsContext gc;// This is what you need to draw on canvas.
    private GraphicsContext gc2;
    private GraphicsContext gc3;
    public creature creaturearray[];
    public creature creaturearrayscore[];
    public int recordpoi;
    public resource background = new resource();
    public int backgroundrecord[][][] = new int[20][20][1000];
    public int quan;
    public int strucdemo[] = new int[2];
    public int showinginfo = -1;
    public int mo;
    public int fa;
    public GameManager(GraphicsContext gc, GraphicsContext gc2, GraphicsContext gc3,int quan){
    	this.gc = gc;
    	this.gc2 = gc2;
    	this.gc3 = gc3;
    	this.quan = quan;
    	this.strucdemo[0] = 9;
    	//this.strucdemo[1] = 7;
    	this.strucdemo[1] = 5;
    	creaturearray = new creature[quan];
    	recordpoi=1005;
    	for(int xxx=-1;xxx++<19;){
    		for(int yyy=-1;yyy++<19;){
    			for(int zzz=-1;zzz++<999;){
    				backgroundrecord[xxx][yyy][zzz]=75;
    			}
    		}
    	}
    }

    // Draw method, being called at Controller's animation timer.
    /*public void draw(long timestamp){
        final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
        lastUpdateTime = timestamp;
    }*/

    public void create(){
    	for(int n = -1; n++ < quan-1;){
    		creaturearray[n] = new creature(strucdemo);
    	}
    }
    public void scoreboard(){
    	creaturearrayscore = Arrays.copyOf(creaturearray, creaturearray.length);
    	Arrays.sort(creaturearrayscore);
    }
    public void nextpos(long timestamp){
    	if(lastUpdateTime == 0){
    		lastUpdateTime = timestamp;
    	}
    	for(int n = -1; n++ < quan-1;){
    		if(creaturearray[n].brain.out[creaturearray[n].struc.length - 1][3] > creaturearray[n].brain.out[creaturearray[n].struc.length - 1][4]){
       			if(creaturearray[n].ren != -1){
       				creaturearray[creaturearray[n].ren].power -= creaturearray[n].power*0.75;
       			}
       			if(creaturearray[n].len != -1){
       				creaturearray[creaturearray[n].len].power -= creaturearray[n].power*0.75;
       			}
       			if(creaturearray[n].fen != -1){
       				creaturearray[creaturearray[n].fen].power -= creaturearray[n].power*0.75;
       			}
       		}
    	}
    	for(int n = -1; n++ < quan-1;){
    		creaturearray[n].power -= creaturearray[n].power*(0.05+0.1*(creaturearray[n].speed+0.1*creaturearray[n].brain.out[creaturearray[n].struc.length - 1][2]-0.5)*(creaturearray[n].speed+0.1*creaturearray[n].brain.out[creaturearray[n].struc.length - 1][2]-0.5)*4)+5;
    		creaturearray[n].getxyspd();
    		creaturearray[n].xpos += creaturearray[n].xspd * 200*(timestamp - lastUpdateTime) / 1_000_000_000.0 ;
    		creaturearray[n].ypos += creaturearray[n].yspd * 200*(timestamp - lastUpdateTime) / 1_000_000_000.0 ;
    		if(creaturearray[n].power <= 0){
				mo = -1;
				fa = -1;
				double tempran = Math.random();
				if(tempran > 0.25){
					for(int k = quan; k -->0;){
						if(mo == -1){
							mo = k;
						}else if(creaturearrayscore[k].struc == creaturearrayscore[mo].struc && Math.random() > 0.05){
							fa = k;
							creaturearray[n] = new creature(creaturearrayscore[k].struc);
							creaturearray[n].xpos = Math.random()*999;
							creaturearray[n].ypos = Math.random()*999;
							creaturearray[n].angle = Math.random()*2*Math.PI;
							creaturearray[n].speed = Math.random()*100;
							creaturearray[n].rr = (creaturearrayscore[mo].rr+creaturearrayscore[fa].rr)/2;
							creaturearray[n].gg = (creaturearrayscore[mo].gg+creaturearrayscore[fa].gg)/2;
							creaturearray[n].bb = (creaturearrayscore[mo].bb+creaturearrayscore[fa].bb)/2;
							creaturearray[n].dna(creaturearrayscore[mo].brain.narray, creaturearrayscore[fa].brain.narray);
							creaturearray[n].gen=Math.max(creaturearrayscore[mo].gen,creaturearrayscore[fa].gen)+1;
							break;
						}
					}
				}else if(tempran > 0.1){
					int temptmp;
					if(Math.random() > 0.8 || creaturearrayscore[quan-1].struc.length == 2){
						temptmp = creaturearrayscore[quan-1].struc.length+1;
					}else{
						temptmp = creaturearrayscore[quan-1].struc.length;
					}
					int tempstruc[] = new int[temptmp];
					tempstruc[0]=9;
					tempstruc[temptmp-1]=5;
					for(int str = 0; str++ < temptmp-2;){
						tempstruc[str]=((int)(Math.random()*4)+5);
					}
					creaturearray[n] = new creature(tempstruc);
					creaturearray[n].xpos = Math.random()*999;
					creaturearray[n].ypos = Math.random()*999;
					creaturearray[n].angle = Math.random()*2*Math.PI;
					creaturearray[n].speed = Math.random()*100;
				}else{
					creaturearray[n] = new creature(creaturearrayscore[quan-1].struc);
					creaturearray[n].xpos = Math.random()*999;
					creaturearray[n].ypos = Math.random()*999;
					creaturearray[n].angle = Math.random()*2*Math.PI;
					creaturearray[n].speed = Math.random()*100;
				}
    			creaturearray[n].getxyspd();
			}
    		if(creaturearray[n].xpos > 999 || creaturearray[n].xpos < 0){
    			creaturearray[n].angle = (- creaturearray[n].angle) + Math.PI;
    			if(creaturearray[n].xpos < 0){
    				creaturearray[n].xpos = - creaturearray[n].xpos;
    			}else
    			{
    				creaturearray[n].xpos = 1999 - creaturearray[n].xpos;
    			}
    		}
    		if(creaturearray[n].ypos > 999 || creaturearray[n].ypos < 0){
    			creaturearray[n].angle = (- creaturearray[n].angle);
    			if(creaturearray[n].ypos < 0){
    				creaturearray[n].ypos = - creaturearray[n].ypos;
    			}else
    			{
    				creaturearray[n].ypos = 1999 - creaturearray[n].ypos;
    			}
    		}
    		
    	}
    	for(int n = -1; n++ < quan-1;){
    		for(int m = -1; m++ < quan-1;){
    			if((creaturearray[n].xpos-creaturearray[m].xpos)*(creaturearray[n].xpos-creaturearray[m].xpos)+(creaturearray[n].ypos-creaturearray[m].ypos)*(creaturearray[n].ypos-creaturearray[m].ypos)<(creaturearray[n].rad+creaturearray[m].rad)*(creaturearray[n].rad+creaturearray[m].rad) && n!=m){
      				double tempt = 0;
    				double dd = Math.pow((creaturearray[n].xpos-creaturearray[m].xpos)*(creaturearray[n].xpos-creaturearray[m].xpos)+(creaturearray[n].ypos-creaturearray[m].ypos)*(creaturearray[n].ypos-creaturearray[m].ypos), 0.5);
					double aa = creaturearray[n].power + creaturearray[m].power;
					if((dd-Math.pow((2*aa-dd*dd), 0.5))/2 > 0 && (2*aa+2*dd*Math.pow((2*aa-dd*dd),0.5))/4 <= 625){
						tempt = (2*aa-2*dd*Math.pow((2*aa-dd*dd),0.5))/4;
					}else if(dd-25 > 0){
						tempt = (dd-25)*(dd-25);
					}else if((dd-Math.pow((2*aa-dd*dd), 0.5))/2 <= 0 && (2*aa+2*dd*Math.pow((2*aa-dd*dd),0.5))/4 <= 625){
						tempt = 0;
					}
    				if(creaturearray[n].power>creaturearray[m].power){
    					creaturearray[n].power += creaturearray[m].power - tempt;
    					creaturearray[m].power = tempt;
    					creaturearray[n].rad = Math.pow(creaturearray[n].power, 0.5);
    					if(creaturearray[n].rad > 25){
    						creaturearray[n].rad = 25;
    					}
    					creaturearray[m].rad = Math.pow(tempt, 0.5)-0.00000001;		//error
    					if(creaturearray[m].power <= 0){
    						mo = -1;
    						fa = -1;
    						double tempran = Math.random();
    						if(tempran > 0.2){
	    						for(int k = quan; k -->0;){
	    							if(mo == -1){
	    								mo = k;
	    							}else if(creaturearrayscore[k].struc == creaturearrayscore[mo].struc && Math.random() > 0.05){
	    								fa = k;
	    								creaturearray[m] = new creature(creaturearrayscore[k].struc);
	    	    						creaturearray[m].xpos = Math.random()*999;
	    	    						creaturearray[m].ypos = Math.random()*999;
	    	    						creaturearray[m].angle = Math.random()*2*Math.PI;
	    	    						creaturearray[m].speed = Math.random()*100;
	    								creaturearray[m].rr = (creaturearrayscore[mo].rr+creaturearrayscore[fa].rr)/2;
	    								creaturearray[m].gg = (creaturearrayscore[mo].gg+creaturearrayscore[fa].gg)/2;
	    								creaturearray[m].bb = (creaturearrayscore[mo].bb+creaturearrayscore[fa].bb)/2;
	    								creaturearray[m].dna(creaturearrayscore[mo].brain.narray, creaturearrayscore[fa].brain.narray);
	    								creaturearray[m].gen=Math.max(creaturearrayscore[mo].gen,creaturearrayscore[fa].gen)+1;
	    								break;
	    							}
	    						}
    						}else if(tempran > 0.1){
    							int temptmp;
    							if(Math.random() > 0.8 || creaturearrayscore[quan-1].struc.length == 2){
    								temptmp = creaturearrayscore[quan-1].struc.length+1;
    							}else{
    								temptmp = creaturearrayscore[quan-1].struc.length;
    							}
    							int tempstruc[] = new int[temptmp];
    							tempstruc[0]=9;
    							tempstruc[temptmp-1]=5;
    							for(int str = 0; str++ < temptmp-2;){
    								tempstruc[str]=((int)(Math.random()*4)+5);
    							}
    							creaturearray[m] = new creature(tempstruc);
    							creaturearray[m].xpos = Math.random()*999;
    							creaturearray[m].ypos = Math.random()*999;
    							creaturearray[m].angle = Math.random()*2*Math.PI;
    							creaturearray[m].speed = Math.random()*100;
    						}else{
    							creaturearray[m] = new creature(creaturearrayscore[quan-1].struc);
    							creaturearray[m].xpos = Math.random()*999;
    							creaturearray[m].ypos = Math.random()*999;
    							creaturearray[m].angle = Math.random()*2*Math.PI;
    							creaturearray[m].speed = Math.random()*100;
    						}
    		    			creaturearray[m].getxyspd();
    					}
    				}else{
    					creaturearray[m].power += creaturearray[n].power - tempt;
    					creaturearray[n].power = tempt;
    					creaturearray[m].rad = Math.pow(creaturearray[n].power, 0.5);
    					if(creaturearray[m].rad > 25){
    						creaturearray[m].rad = 25;
    					}
    					creaturearray[n].rad = Math.pow(tempt, 0.5)-0.00000001;		//error
    					if(creaturearray[n].power <= 0){
	    		    		mo = -1;
	    					fa = -1;
	    					double tempran = Math.random();
	    					if(tempran > 0.2){
		    					for(int k = quan; k -->0;){
		    						if(mo == -1){
		    							mo = k;
		    						}else if(creaturearrayscore[k].struc == creaturearrayscore[mo].struc && Math.random() > 0.05){
		    							fa = k;
		    							creaturearray[n] = new creature(creaturearrayscore[k].struc);
		    							creaturearray[n].xpos = Math.random()*999;
		    							creaturearray[n].ypos = Math.random()*999;
		    							creaturearray[n].angle = Math.random()*2*Math.PI;
		    							creaturearray[n].speed = Math.random()*100;
		    							creaturearray[n].rr = (creaturearrayscore[mo].rr+creaturearrayscore[fa].rr)/2;
		    							creaturearray[n].gg = (creaturearrayscore[mo].gg+creaturearrayscore[fa].gg)/2;
		    							creaturearray[n].bb = (creaturearrayscore[mo].bb+creaturearrayscore[fa].bb)/2;
		    							creaturearray[n].dna(creaturearrayscore[mo].brain.narray, creaturearrayscore[fa].brain.narray);
		    							creaturearray[n].gen=Math.max(creaturearrayscore[mo].gen,creaturearrayscore[fa].gen)+1;
		    							break;
		    						}
		    					}
	    					}else if(tempran > 0.1){
	    						int temptmp;
	    						if(Math.random() > 0.8 || creaturearrayscore[quan-1].struc.length == 2){
	    							temptmp = creaturearrayscore[quan-1].struc.length+1;
	    						}else{
	    							temptmp = creaturearrayscore[quan-1].struc.length;
	    						}
	    						int tempstruc[] = new int[temptmp];
	    						tempstruc[0]=9;
	    						tempstruc[temptmp-1]=5;
	    						for(int str = 0; str++ < temptmp-2;){
	    							tempstruc[str]=((int)(Math.random()*4)+5);
	    						}
	    						creaturearray[n] = new creature(tempstruc);
	    						creaturearray[n].xpos = Math.random()*999;
	    						creaturearray[n].ypos = Math.random()*999;
	    						creaturearray[n].angle = Math.random()*2*Math.PI;
	    						creaturearray[n].speed = Math.random()*100;
	    					}else{
	    						creaturearray[n] = new creature(creaturearrayscore[quan-1].struc);
	    						creaturearray[n].xpos = Math.random()*999;
	    						creaturearray[n].ypos = Math.random()*999;
	    						creaturearray[n].angle = Math.random()*2*Math.PI;
	    						creaturearray[n].speed = Math.random()*100;
	    					}
	    		    		creaturearray[n].getxyspd();
    					}
    				}
    				if(m < n){
						n = m;
						break;
					}
        		}
    		}
    	}
    	lastUpdateTime = timestamp;
    }
    
    public void initial(){
    	for(int n = -1; n++ < quan-1;){
    		creaturearray[n].xpos = Math.random()*999;
    		creaturearray[n].ypos = Math.random()*999;
    		creaturearray[n].angle = Math.random()*2*Math.PI;
    		creaturearray[n].speed = Math.random()*100;
    		creaturearray[n].getxyspd();
    	}
    }
    
    public void thinking(){
    	double a;
    	double b;
    	double c;
    	for(int n = -1; n++ < quan-1;){
    		double d = 0;
        	double e = 0;
        	double f = 0;
    		if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4)) > 999) || ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4)) < 0) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4)) > 999) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4)) < 0)){
    			a=75;
    		}else{
    			a = background.data[(int) ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4))/50)][(int) ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4))/50)][0];
    		}
    		
    		if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4)) > 999) || ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4)) < 0) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4)) > 999) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4)) < 0)){
    			b=75;
    		}else{
    			b = background.data[(int) ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4))/50)][(int) ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4))/50)][0];
    		}
    		
    		if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle)) > 999) || ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle)) < 0) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle)) > 999) || ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle)) < 0)){
    			c=75;
    		}else{
    			c = background.data[(int) ((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle))/50)][(int) ((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle))/50)][0];
    		}
    		creaturearray[n].ren=-1;
    		creaturearray[n].len=-1;
    		creaturearray[n].fen=-1;
    		for(int m = -1; m++ < quan-1;){
    			if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4))-creaturearray[m].xpos)*((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4))-creaturearray[m].xpos)+((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4))-creaturearray[m].ypos)*((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4))-creaturearray[m].ypos) < creaturearray[m].rad*creaturearray[m].rad){
    				d += creaturearray[m].power;
    				creaturearray[n].ren=m;
    			}
    			if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4))-creaturearray[m].xpos)*((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4))-creaturearray[m].xpos)+((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4))-creaturearray[m].ypos)*((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4))-creaturearray[m].ypos) < creaturearray[m].rad*creaturearray[m].rad){
    				e += creaturearray[m].power;
    				creaturearray[n].len=m;
    			}
    			if(((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle))-creaturearray[m].xpos)*((creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle))-creaturearray[m].xpos)+((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle))-creaturearray[m].ypos)*((creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle))-creaturearray[m].ypos) < creaturearray[m].rad*creaturearray[m].rad){
    				f += creaturearray[m].power;
    				creaturearray[n].fen=m;
    			}
    		}
       		creaturearray[n].think(((double)(background.data[(int) (creaturearray[n].ypos/50)][(int) (creaturearray[n].xpos/50)][0]-75))/175, (double)(a-75)/175, (double)(b-75)/175, (double)(c-75)/175, d/1400, e/1400, f/1400);
       		if(creaturearray[n].brain.out[creaturearray[n].struc.length - 1][3] <= creaturearray[n].brain.out[creaturearray[n].struc.length - 1][4] && background.data[(int) (creaturearray[n].ypos/50)][(int) (creaturearray[n].xpos/50)][0]>75){
       			background.data[(int) (creaturearray[n].ypos/50)][(int) (creaturearray[n].xpos/50)][0]--;
       		}
       		
    	}
    }
    public void update(){
    	for(int n = -1; n++ < 19;){
    		for(int m = -1; m++ < 19;){
    			if(Math.random() < (0.05+(0.1*(background.data[n][m][1]-75)/175))*quan/30 && background.data[n][m][0]<250){
    				background.data[n][m][0]++;
    			}
    			backgroundrecord[n][m][recordpoi%1000]=background.data[n][m][0];
    			gc3.setFill(Color.rgb(75,background.data[n][m][0],75));
    			gc3.fillRect(m*50, n*50, 50, 50);
    		}
    	} 
		if(showinginfo >= quan){
			gc3.setStroke(Color.RED);
			gc3.setLineWidth(3);
			gc3.strokeRect(((showinginfo-quan)%20)*50, ((showinginfo-quan)/20)*50, 50, 50);
		}
    	gc.setFill(Color.BISQUE);
    	gc.setLineWidth(3);
    	gc.setStroke(Color.BLACK);
		
    	for(int n = -1; n++ < quan-1;){
    		creaturearray[n].record[recordpoi%1000]=creaturearray[n].power;
    		creaturearray[n].rad = Math.pow(creaturearray[n].power, 0.5);
    		if(creaturearray[n].power>625){
    			creaturearray[n].rad = 25;
    		}
    		gc.setFill(Color.rgb((int)(creaturearray[n].rr*255),(int)(creaturearray[n].gg*255),(int)(creaturearray[n].bb*255)));
    		gc.fillOval(creaturearray[n].xpos-creaturearray[n].rad, creaturearray[n].ypos-creaturearray[n].rad, creaturearray[n].rad*2, creaturearray[n].rad*2);
    		if(n == showinginfo){
    			gc.setStroke(Color.RED);
        		gc.strokeOval(creaturearray[n].xpos-creaturearray[n].rad, creaturearray[n].ypos-creaturearray[n].rad, creaturearray[n].rad*2, creaturearray[n].rad*2);
        		gc.setStroke(Color.BLACK);
    		}else{
        		gc.strokeOval(creaturearray[n].xpos-creaturearray[n].rad, creaturearray[n].ypos-creaturearray[n].rad, creaturearray[n].rad*2, creaturearray[n].rad*2);
    		}
    		if(showinginfo == -1){
    			gc.setStroke(Color.YELLOW);
        		gc.strokeOval(creaturearrayscore[quan-1].xpos-creaturearrayscore[quan-1].rad, creaturearrayscore[quan-1].ypos-creaturearrayscore[quan-1].rad, creaturearrayscore[quan-1].rad*2, creaturearrayscore[quan-1].rad*2);
        		gc.setStroke(Color.BLACK);
    		}
    	}
    	recordpoi++;
    	for(int n = -1; n++ < quan-1;){
    		if(creaturearray[n].brain.out[creaturearray[n].struc.length - 1][3] > creaturearray[n].brain.out[creaturearray[n].struc.length - 1][4]){
    			gc.setStroke(Color.RED);
    		}else{
    			gc.setStroke(Color.BLACK);
    		}
    		gc.strokeLine(creaturearray[n].xpos+(1+creaturearray[n].rad)*Math.cos(creaturearray[n].angle+Math.PI/4),creaturearray[n].ypos+(1+creaturearray[n].rad)*Math.sin(creaturearray[n].angle+Math.PI/4),creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle+Math.PI/4),creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle+Math.PI/4));
    		gc.strokeLine(creaturearray[n].xpos+(1+creaturearray[n].rad)*Math.cos(creaturearray[n].angle-Math.PI/4),creaturearray[n].ypos+(1+creaturearray[n].rad)*Math.sin(creaturearray[n].angle-Math.PI/4),creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle-Math.PI/4),creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle-Math.PI/4));
    		gc.strokeLine(creaturearray[n].xpos+(1+creaturearray[n].rad)*Math.cos(creaturearray[n].angle),creaturearray[n].ypos+(1+creaturearray[n].rad)*Math.sin(creaturearray[n].angle),creaturearray[n].xpos+creaturearray[n].rad*2*Math.cos(creaturearray[n].angle),creaturearray[n].ypos+creaturearray[n].rad*2*Math.sin(creaturearray[n].angle));
    	}
    	gc2.clearRect(0, 0, 500, 1000);
    	gc2.setLineWidth(2);
    	gc2.setStroke(Color.rgb(0, 0, 0));
    	if(showinginfo >= quan){
    		gc2.setFont(Font.font ("Verdana", 36));
    		gc2.strokeText("grow speed", 150, 100);
    		gc2.strokeText(String.valueOf(background.data[(showinginfo-quan)/20][(showinginfo-quan)%20][1]-75),220, 150);
    		gc2.strokeLine(0, 300, 1000, 300);
    		gc2.strokeLine(0, 600, 1000, 600);
    		for(int tempng = recordpoi; tempng-- > recordpoi-998 ;){
				gc2.strokeLine(10-tempng+recordpoi, 600-((backgroundrecord[(showinginfo-quan)/20][(showinginfo-quan)%20][tempng%1000])-75)*300/175, 11-tempng+recordpoi, 600-((backgroundrecord[(showinginfo-quan)/20][(showinginfo-quan)%20][(tempng-1)%1000])-75)*300/175);
			}
    	}else{
    		if(showinginfo < quan){
    			gc2.setStroke(Color.rgb(0, 0, 0));
    			int yyyy = quan*(250/quan)+10;
    			gc2.strokeLine(10,10,10,yyyy);
    			gc2.strokeLine(10,yyyy,1000,yyyy);
    			gc2.strokeLine(10, yyyy, 10+creaturearrayscore[0].lifetime, yyyy-1*(250/quan));
    			for(int n = 0; n++ < quan-1;){
    				gc2.strokeLine(10+creaturearrayscore[n-1].lifetime, yyyy-n*(250/quan), 10+creaturearrayscore[n].lifetime, yyyy-(n+1)*(250/quan));
    			}
    			if(showinginfo == -1){
    				float tempwe = 300/(creaturearrayscore[quan-1].struc.length-1);
    				for(int tempnng = -1; tempnng++ < creaturearrayscore[quan-1].struc.length-1;){
    					for(int tempnng2 = -1; tempnng2++ < creaturearrayscore[quan-1].struc[tempnng]-1;){
    						float temphi = 350/(creaturearrayscore[quan-1].struc[tempnng]-1);
    						if(tempnng != creaturearrayscore[quan-1].struc.length-1){
    							for(int tempnng3 = -1; tempnng3++ < creaturearrayscore[quan-1].struc[tempnng+1]-1;){
    								int tempttt = (int)(255*(creaturearrayscore[quan-1].brain.narray[tempnng][tempnng3].w[tempnng2]));
    								if(tempttt>=0){
    									gc2.setStroke(Color.rgb(tempttt, tempttt, tempttt));
    								}else{
    									gc2.setStroke(Color.rgb(-tempttt, 0, 0));
    								}
    								gc2.strokeLine(50+tempnng*tempwe+tempwe*0.3/2, 300+temphi*tempnng2+temphi*0.8/2, 50+(tempnng+1)*tempwe+tempwe*0.3/2, 300+(350/(creaturearrayscore[quan-1].struc[tempnng+1]-1))*(tempnng3+0.8/2));
    							}
    						}
    						gc2.setFill(Color.rgb(150,100,(int)(510/(1+Math.exp(creaturearrayscore[quan-1].brain.out[tempnng][tempnng2]))-1)));
    			    		gc2.fillOval(50+tempnng*tempwe, 300+temphi*tempnng2, tempwe*0.3, temphi*0.8);
    					}
    				}
    				gc2.setStroke(Color.rgb(0, 0, 0));
    				gc2.strokeLine(10,990,1000,990);
    				for(int tempng = recordpoi; tempng-- > recordpoi-998 ;){
    					gc2.strokeLine(10-tempng+recordpoi, 990-(creaturearrayscore[quan-1].record[tempng%1000])*200/1400, 11-tempng+recordpoi, 990-(creaturearrayscore[quan-1].record[(tempng-1)%1000])*200/1400);
    				}
    				double tempsum=0;
    				for(int ny=-1;ny++<quan-1;){
    					tempsum+=creaturearray[ny].lifetime;
    				}
    				gc2.setFont(Font.font ("Verdana", 20));
    				gc2.strokeText("average  = ",170, 20);
    				gc2.strokeText(String.valueOf(tempsum/quan),300, 20);
    				gc2.strokeText("power  = ",170, 760);
    				gc2.strokeText(String.format("%.2f", creaturearrayscore[quan-1].power),280, 760);
    			}else{
    				float tempwe = 300/(creaturearray[showinginfo].struc.length-1);
    				for(int tempnng = -1; tempnng++ < creaturearray[showinginfo].struc.length-1;){
    					for(int tempnng2 = -1; tempnng2++ < creaturearray[showinginfo].struc[tempnng]-1;){
    						float temphi = 350/(creaturearray[showinginfo].struc[tempnng]-1);
    						if(tempnng != creaturearray[showinginfo].struc.length-1){
    							for(int tempnng3 = -1; tempnng3++ < creaturearray[showinginfo].struc[tempnng+1]-1;){
    								int tempttt = (int)(255*(creaturearray[showinginfo].brain.narray[tempnng][tempnng3].w[tempnng2]));
    								if(tempttt>=0){
    									gc2.setStroke(Color.rgb(tempttt, tempttt, tempttt));
    								}else{
    									gc2.setStroke(Color.rgb(-tempttt, 0, 0));
    								}
    								gc2.strokeLine(50+tempnng*tempwe+tempwe*0.3/2, 300+temphi*tempnng2+temphi*0.8/2, 50+(tempnng+1)*tempwe+tempwe*0.3/2, 300+(350/(creaturearray[showinginfo].struc[tempnng+1]-1))*(tempnng3+0.8/2));
    							}
    						}
    						gc2.setFill(Color.rgb(150,100,(int)(510/(1+Math.exp(creaturearray[showinginfo].brain.out[tempnng][tempnng2]))-1)));
    			    		gc2.fillOval(50+tempnng*tempwe, 300+temphi*tempnng2, tempwe*0.3, temphi*0.8);
    					}
    				}
    				gc2.setStroke(Color.rgb(0, 0, 0));
    				gc2.strokeLine(10,990,1000,990);
    				for(int tempng = recordpoi; tempng-- > recordpoi-998 ;){
    					gc2.strokeLine(10-tempng+recordpoi, 990-(creaturearray[showinginfo].record[tempng%1000])*200/1400, 11-tempng+recordpoi, 990-(creaturearray[showinginfo].record[(tempng-1)%1000])*200/1400);
    				}
    				double tempsum=0;
    				for(int ny=-1;ny++<quan-1;){
    					tempsum+=creaturearray[ny].lifetime;
    				}
    				gc2.setFont(Font.font ("Verdana", 20));
    				gc2.strokeText("average  = ",170, 20);
    				gc2.strokeText(String.valueOf(tempsum/quan),300, 20);
    				gc2.strokeText("# ",220, 270);
    				for(int nkn=quan;nkn-->0;){
    					if(creaturearrayscore[nkn].lifetime<=creaturearray[showinginfo].lifetime){
    						gc2.strokeText(String.valueOf(quan-nkn),260, 270);
    						break;
    					}
    				}
    				gc2.strokeText(String.valueOf(tempsum/quan),300, 20);
    				gc2.strokeText("power  = ",170, 760);
    				gc2.strokeText(String.format("%.2f", creaturearray[showinginfo].power),280, 760);
    			}
    		}
    	}
    }
    public void up(){
        if(showinginfo >= quan && background.data[(showinginfo-quan)/20][(showinginfo-quan)%20][1] < 250){
        	background.data[(showinginfo-quan)/20][(showinginfo-quan)%20][1] ++;
        }
    }
    public void down(){
    	if(showinginfo >= quan && background.data[(showinginfo-quan)/20][(showinginfo-quan)%20][1] > 75){
        	background.data[(showinginfo-quan)/20][(showinginfo-quan)%20][1] --;
        }
    }
    public void movePlayerY(int y){
    	//
    }

    //check every click on canvas if the GameObject is clicked or not.
    public void OnClick(MouseEvent event){
        double checkx = event.getSceneX();
        double checky = event.getSceneY();
        double checking = 0;
        for(int n = -1; n++ < quan-1;){
        	if((checkx-creaturearray[n].xpos)*(checkx-creaturearray[n].xpos)+(checky-creaturearray[n].ypos)*(checky-creaturearray[n].ypos)<creaturearray[n].rad*creaturearray[n].rad){
        		if(showinginfo == n){
        			showinginfo = -1;
        		}else{
        			showinginfo = n;
        		}
        		checking = 1;
        	}
        }
        if(checking == 0){
        	if(showinginfo == (quan + (int)(checky / 50) * 20 + (int)(checkx / 50))){
        		showinginfo = -1;
        	}else{
        		showinginfo = (quan + (int)(checky / 50) * 20 + (int)(checkx / 50));
        	}
        }
    }
}