/*
 * Kári Snær Kárason - ksk12@hi.is
 * JuggleQuest lokaverkefni - 10/04/2016
 * 
 * Þessi klasi heldur utan um það sem skeður í leiknum. Hann skilar því hvort
 * tiltekinn reitur inniheldur einhvern viðburð (event) og hvaða hluti/áfanga
 * leikmaður hefur fengið/lokið (items).
 */
package is.hi.Lokaverkefni.vinnsla;

import is.hi.Lokaverkefni.vidmot.JuggleQuest;

/**
 *
 * @author Kári
 */
public class GameInformation {
    private final JuggleQuest juggleMain;
    //array that holds events tied to squares
    private int[][] events = new int[8][12];
    //array that holds items/tasks tied to the game
    private boolean[] items = new boolean[35];

    public GameInformation(JuggleQuest gluggi){
        juggleMain = gluggi;
        for(int i=0; i<events.length; i++){
            for(int j=0; j<events[0].length; j++){
                events[i][j]=-1;
            }
        }
        reset();
    }
    //changes what items player has/what tasks he has finished
    public void setItem(int x, int y){
        //has seen poop
        if(x==0&&(y==1||y==2)) {items[0]=true;}                    
        //dad is gone
        if(x==1&&y==4&&items[0]){ items[1]=true; events[1][1]=-1; juggleMain.changeMap(1);}  
        //has seen ogre
        if(x==5) {items[3]=true;}                                  
        //flowerpetal
        if(x==4 &&y==2) {items[2]=true;}                           
        //meat
        if(x==2&&y==4&&items[3]) {items[4]=true;}
        //ogre is asleep
        if(x==5&&y==4) {items[6]=true; items[2]=false; items[4]=false; events[0][4]=-1; juggleMain.changeMap(0);}
        //elf 1 gone
        if(x==7&&y==2) {items[8]=true; events[2][6]=-1;}
        //elf 2 gone
        if(x==8&&(y==1||y==4)){items[9]=true; events[2][9]=-1;}
        //elf 3 gone
        if(x==9&&y==2){items[10]=true; events[3][9]=-1;}
        //wrong guess
        if(x==9&&(y==1||y==4)){items[11]=true; }
        //first guess
        if(x==9&&y==2&&!items[11]){items[12]=true; events[3][9]=27;}
        //flute
        if(x==27&&(y==1||y==2||y==3)&&items[12]){ items[13]=true; items[12]=false;}
        if((x==27&&y==4)||(x==11&&y==4)||(x==15&&y==4)){items[13]=false;}
        //shook hand
        if(x==10&&y==1){items[14]=true;}
        //talked to man
        if(x==10){items[15]=true;}
        //dealt with square
        if(x==11){items[16]=true; events[3][10]=-1; events[3][7]=-1;}
        //rung bell
        if(x==13&&y==1){items[17]=true;}
        //moat down
        if(x==16&&y==1&&items[19]){items[18]=true; juggleMain.changeMap(7);}
        //letter
        if(x==28&&y==1){items[19]=true;}
        //final trick
        if(x==23&&items[21]){ items[22] = true;}
        //2 tricks
        if(x==23&&items[20]){ items[21] = true;}
        //1 trick
        if(x==23){items[20]=true;}
      
        //death by ogre
        if(x==5&&y==2){items[30]=true;}
        //told dad truth
        if(x==1&&y==1){items[31]=true;}
        //engulfed in stone
        if(x==11&&y==2&&items[14]){items[32]=true;}
        //man dead
        if(x==11&&(y==1||y==4)){ items[33]=true;}
        
        //flown over moat
        if(x==15&&(y==4||y==1)){ juggleMain.setPos(6,5);}
        
        //new game
        if((x==1&&y==3&&items[31])||(x==5&&y==3&&items[30])
           ||(x==11&&y==3&&items[32])||(x==23&&y==3&&items[22])){juggleMain.newGame();}
        
        //quit
        if((x==1&&y==4&&items[31])||(x==5&&y==4&&items[30])||(x==11&&y==4&&items[32])
           ||(x==23&&y==4&&items[22])){System.exit(0);}
        
    }
    //returns whether player has item x/has accomplished task x
    public boolean getItem(int x){
        return items[x];
    }
    //returns event of square if there is one
    public int getEvent(int b, int r){
        return events[b][r];
    }
    //removes finished event
    public void setEvent(int b, int r){
        events[b][r]=0;
    }
    //resets game
    public void reset(){
        for(int i=0; i<items.length; i++){
            items[i]=false;
        }
        
        events[0][1]=0;     // poo in pool
        events[1][1]=1;     // meet dad
        events[1][3]=2;     // butcher
        events[1][7]=3;     // river
        events[1][6]=4;     // flower
        events[0][4]=5;     // ogre
        events[3][5]=6;     // forrest
        events[2][6]=7;     // elf 1
        events[2][8]=8;     // elf 2
        events[2][10]=9;    // elf 3
        events[3][10]=10;   // village man 1
        events[3][7]=11;    // village man 2
        events[5][7]=12;    // mountains
        events[5][5]=13;    // bell
        events[4][6]=-1;    // waterfall
        events[6][1]=15;    // moat
        events[7][2]=16;    // moat crossing
        events[6][8]=17;    // Revelle entrance
        events[6][10]=18;   // Revelle 1
        events[7][9]=19;    // Revelle 2
        events[7][11]=20;   // Finale
        
        
        //additional events
        events[0][6]=25;    // man in boat
        events[2][4]=26;    // bear trap
        events[3][9]=-1;    // flute
        events[0][0]=28;    // forgot letter
        
        
    }
    //returns which items player has
    public boolean[] getInventory(){
        boolean inventory[] = new boolean[]{items[2], items[4], items[13], items[19]};
        return inventory;
    }
}
