/*
 * Kári Snær Kárason - ksk12@hi.is
 * JuggleQuest lokaverkefni - 10/04/2016
 * 
 * Þessi klasi inniheldur upplýsingar um leikborðið. Hann skilar því hvort
 * leikmaður má hreyfa sig í ákveðna átt, eftir því hvar hann er staddur, og
 * einnig því hvort birta eigi nýtt leikborð eftir að leikmaður hefur fært sig.
 * Klasinn heldur loks utan um á hvaða svæði leikmaður er staddur.
 */
package is.hi.Lokaverkefni.vinnsla;

import is.hi.Lokaverkefni.vidmot.JuggleQuest;

/**
 *
 * @author Kári
 */
public class MapInformation {
    private final JuggleQuest juggleMain;
    //variable for current area
    private String area = "The Town of Flabbergast";
    
    public MapInformation(JuggleQuest wind){
        juggleMain = wind;

    }
    //method that returns whether the player can move at current pos and in what directions
    public boolean[] canMove(int b, int r){   
        boolean[] mov = new boolean[] {false,true,false,true};
        
        //w-Up
        if((b==1&&r==3)||(b==0&&r==4&&juggleMain.getInfo(6))||(b==3&&r==1)
            ||(b==2&&r==4)||(b==3&&(r==6||(r==7&&juggleMain.getInfo(16))))
            ||(b==4&&r==4)||(b==5&&r==3)||(b==6&&r==4)
            ||(b==7&&r==2&&juggleMain.getInfo(18))) mov[0] = true;
        
        //A-Left
        if(((r+1)%4==0)||(b==3 && (r==7 || r==10||r==5))||(b==5 && r==2)
            ||(b==2&&r==8&&!juggleMain.getInfo(9))
            ||(b==2&&r==10&&!juggleMain.getInfo(10))
            ||(b==1&&r==1&&!juggleMain.getInfo(1))) mov[1] = false;
        
        //S-Down
        if((b==0&&r==8)||(b==1&&r==7)||(b==3&&(r==5||(r==10&&juggleMain.getInfo(15))
            ||r==11))||(b==4&&r==8)||(b==2&&r==8)||(b==5&&r==7)
            ||(b==6&&r==8)||(b==7&&r==6&&juggleMain.getInfo(18))) mov[2]=true;
        
        //D-Right
        if((r==0)||(r==4)||(r==8)||(b==3&&(r==8||r==11||r==6))||(b==5&&r==3)
            ||(b==2&&r==6&&!juggleMain.getInfo(8))) mov[3]=false;
    
    
        return mov;
    }
    //returns whether a new mapscreen has been reached
    public int[] newMap(int b, int r){
        int x[] =new int[] {-1,-1};
        if(b==0&&r==0){ x[0]=0; x[1]=0;}
        if(b==0&&r==3){ x[0]=1; x[1]=0;}
        if(b==0&&r==7){ x[0]=1; x[1]=4;}
        if(b==0&&r==8){ x[0]=2; x[1]=0;}
        if(b==0&&r==11){x[0]=1; x[1]=8;}
        
        if(b==1&&r==0){ x[0]=0; x[1]=3;}
        if(b==1&&r==4){ x[0]=0; x[1]=7;}
        if(b==1&&r==9){ x[0]=3; x[1]=1;}
        
        
        if(b==2&&r==0){ x[0]=0; x[1]=8;}
        if(b==2&&r==3){ x[0]=3; x[1]=0;}
        if(b==2&&r==7){ x[0]=3; x[1]=4;}
        if(b==2&&r==11){x[0]=3; x[1]=8;}
        
        if(b==3&&r==0){ x[0]=2; x[1]=3;}
        if(b==3&&r==4){ x[0]=2; x[1]=7;}
        if(b==3&&r==8){ x[0]=2; x[1]=11;}
        if(b==3&&r==11){x[0]=5; x[1]=3;}
        
        if(b==4&&r==7){ x[0]=5; x[1]=4;}
        if(b==4&&r==8){ x[0]=6; x[1]=0;}
        if(b==4&&r==9){ x[0]=6; x[1]=1;}
        
        if(b==5&&r==4){ x[0]=4; x[1]=7;}
        if(b==5&&r==3){ x[0]=3; x[1]=11;}
        
        if(b==6&&r==0){ x[0]=4; x[1]=8;}
        if(b==6&&r==3){ x[0]=7; x[1]=0;}
        if(b==6&&r==7){ x[0]=7; x[1]=4;}
        if(b==6&&r==11){x[0]=7; x[1]=8;}
                
        if(b==7&&r==0){ x[0]=6; x[1]=3;}
        if(b==7&&r==4){ x[0]=6; x[1]=7;}
        if(b==7&&r==8){ x[0]=6; x[1]=11;}
        
        return x;
    }
    //returns which area the player is in
    public String newArea(int b, int r){
        String s=area;
        if((b==1&&r==7)||(b==3&&r==1)){ s = "Frog River"; }
        if(b==1&&r==3){ s = "The Town of Flabbergast"; }
        if((b==3&&r==5)||(b==3&&r==9)){ s = "The Forrest of Many Spooks"; }
        if((b==3&&r==10)||(b==3&&r==11)){ s = "Stonehaven"; }
        if((b==4&&r==4)||(b==5&&r==7)){ s = "Gray Mountains"; }
        if((b==6&&r==0)||(b==6&&r==4)){ s = "Outside of Revelle"; }
        if(b==6&&r==8){ s = "The City of Revelle"; }
        area=s;
        
        return s;
    }
    //resets area at the start of new game
    public void resetArea(){
        area = "The Town of Flabbergast";
    }
}
