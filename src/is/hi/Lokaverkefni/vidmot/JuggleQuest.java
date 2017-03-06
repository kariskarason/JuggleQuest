/*
 * Kári Snær Kárason - ksk12@hi.is
 * JuggleQuest lokaverkefni - 10/04/2016
 * 
 * Þetta er aðal viðmóts klasinn. Hann birtir leikborðið, hluti leikmanns ofl.
 * Hann talar við hina klasana til að stjórna virkni leiksins útfrá því sem
 * leikmaður gerir.
 */
package is.hi.Lokaverkefni.vidmot;

import is.hi.Lokaverkefni.gogn.Music;
import is.hi.Lokaverkefni.vinnsla.GameInformation;
import is.hi.Lokaverkefni.vinnsla.MapInformation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Kári
 */
public class JuggleQuest extends javax.swing.JFrame {
    //instances of other classes
    MapInformation mapInfo = new MapInformation(this);
    GameInformation gameInfo = new GameInformation(this);
    Music music = new Music();
    
    //array for the squares on the map
    private final JLabel[] SQUARES;
    //2 arrays for the icons of the player and inventory items
    private static String ICONFILE[]; 
    private static  ImageIcon ICON[];
    //array for the inventory slots
    private static JLabel[] invSlots;
    //2 arrays for the mapscreens
    private static  String[] MAPFILE;
    private static ImageIcon[] MAP = new ImageIcon[8];
    //variables for the location of the player
    JLabel playerSquare;
    private int nrSquare = 0;
    private int nrMap = 0;


    /**
     * Creates new form AdalGluggi
     */
    public JuggleQuest() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        initComponents();
        this.setLocationRelativeTo(null);
        inspect.setFocusable(false);
        
        //opens mainMenu at the start of the game
        mainMenu();
        this.setVisible(true);
        
        //array with character icons and inventory item icons
        ICONFILE = new String[]{"char.png","ballsIcon.png","flowerIcon.png", "meatIcon.png", "fluteIcon.png", "letterIcon.png"};
        ICON = new ImageIcon[ICONFILE.length];
        for(int i=0; i<ICONFILE.length; i++){
            ICON[i] = new ImageIcon(getClass().getResource(ICONFILE[i]));
        }

        //arrays for the mapscreens
        MAPFILE = new String[]{"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png"};
        for(int i=0; i<8; i++){
            MAP[i] = new ImageIcon(getClass().getResource(MAPFILE[i]));
        }
        
        //array for the map squares
        SQUARES = new JLabel[] {Reitur1, Reitur2, Reitur3, Reitur4, Reitur5,
            Reitur6, Reitur7, Reitur8, Reitur9, Reitur10, Reitur11, Reitur12};
        
        //beginning position of player
        playerSquare = SQUARES[0];
        
        //array for inventory slots
        invSlots = new JLabel[]{slot2, slot3, slot4, slot5};
        
        //plays music at the start of the game
        music.playMusic();
        //shows beginning messages
        sendMsg(30);
        
        //listens for the pressing of WASD/E keys
        addKeyListener(new KeyAdapter(){
        @Override public void keyPressed(KeyEvent e){
            int k = e.getKeyCode();
            if (k==KeyEvent.VK_W && mapInfo.canMove(nrMap, nrSquare)[0]) {
                SQUARES[nrSquare].setIcon(null);
                nrSquare = nrSquare+4;
                SQUARES[nrSquare].setIcon(ICON[0]);                
                }
            if (k==KeyEvent.VK_A && mapInfo.canMove(nrMap, nrSquare)[1]) {
                SQUARES[nrSquare].setIcon(null);
                nrSquare = nrSquare+1;
                SQUARES[nrSquare].setIcon(ICON[0]);               
                }
            if (k==KeyEvent.VK_S && mapInfo.canMove(nrMap, nrSquare)[2]) {
                SQUARES[nrSquare].setIcon(null);
                nrSquare = nrSquare-4;
                SQUARES[nrSquare].setIcon(ICON[0]);
                }
            if (k==KeyEvent.VK_D && mapInfo.canMove(nrMap, nrSquare)[3]) {
                SQUARES[nrSquare].setIcon(null);
                nrSquare = nrSquare-1;
                SQUARES[nrSquare].setIcon(ICON[0]);               
                }
            if (k==KeyEvent.VK_E) {checkSquare();}
            if(k==KeyEvent.VK_W||k==KeyEvent.VK_A||k==KeyEvent.VK_S||
                    k==KeyEvent.VK_D){checkBord();}

            }
        });  
  }
    //checks if the mapscreen needs to be changed and changes settings according to screen
    private void checkBord(){
        //checks if the inspect button should be activated
        if(gameInfo.getEvent(nrMap,nrSquare)!=-1) inspect.setEnabled(true);
        else inspect.setEnabled(false);

        //checks if a new map screen should be shown
        int x[]=mapInfo.newMap(nrMap, nrSquare);
        if(x[0]>=0){ 
            map.setIcon(MAP[x[0]]);
            nrMap = x[0];
            SQUARES[nrSquare].setIcon(null);
            nrSquare=x[1];
            SQUARES[x[1]].setIcon((ICON[0]));
        }
        area.setText(mapInfo.newArea(nrMap, nrSquare));
        changeMusic();
    }
    //changes whether the player has a new item/has finished a new task
    public void changeInfo(int x, int y){
       gameInfo.setItem(x,y);
       inventory();
    }
    //checks whether the player has a certain item/has finished a certain task
    public boolean getInfo(int x){
        return gameInfo.getItem(x);
    }
    //checks if something happens on current square
    private void checkSquare(){
        int event = gameInfo.getEvent(nrMap,nrSquare);
        if(event!=-1) sendMsg(event);   
    }
    //sends which message should be shown to the Messages class
    private void sendMsg(int x){
        Messages message = new Messages(this,true);
        message.changeInfo(x);
        message.setVisible(true);
    }
    //changes the music according to location
    private void changeMusic(){
            if(nrMap==3&&nrSquare==1) music.changeMusic(1);
            if(nrMap==3&&(nrSquare==5||nrSquare==11)) music.changeMusic(2);
            if(nrMap==5&&nrSquare==7) music.changeMusic(3); 
    }
    //updates the inventory slots
    private void inventory(){
        boolean inv[] = gameInfo.getInventory();
 
        for(int i=0;i<invSlots.length;i++){
            invSlots[i].setIcon(null);
        }
        for(int i=0; i<inv.length; i++){
            if(inv[i]){
                for(int j=0; j<invSlots.length; j++){
                    if(invSlots[j].getIcon()==null){
                        invSlots[j].setIcon(ICON[i+2]);
                        break;
                    }
                }
            }
        }
    }
    //starts a new game and resets the game
    public void newGame(){
        mapInfo.resetArea();
        music.changeMusic(1);
        gameInfo.reset();
        SQUARES[nrSquare].setIcon(null);
        nrMap = 0;
        nrSquare = 0;
        
        for(int i=0; i<8; i++){
            MAP[i] = new ImageIcon(getClass().getResource(MAPFILE[i]));
        }
        inventory();
        checkBord();
        sendMsg(30);
    }
    //changes the location of the player in specific cases
    public void setPos(int b, int r){
        SQUARES[nrSquare].setIcon(null);
        nrMap = b;
        nrSquare = r;
        SQUARES[nrSquare].setIcon(ICON[0]);
        checkBord();
    }
    //changes a mapscreen to an alternative one
    public void changeMap(int i){
        String s = (i+1)+"c.png";
        MAP[i] = new ImageIcon(getClass().getResource(s));
        map.setIcon(MAP[i]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inventoryPanel = new javax.swing.JPanel();
        slot1 = new javax.swing.JLabel();
        slot2 = new javax.swing.JLabel();
        slot3 = new javax.swing.JLabel();
        slot4 = new javax.swing.JLabel();
        slot5 = new javax.swing.JLabel();
        charPort = new javax.swing.JLabel();
        inspect = new javax.swing.JButton();
        area = new javax.swing.JLabel();
        mapPanel = new javax.swing.JPanel();
        Reitur12 = new javax.swing.JLabel();
        Reitur11 = new javax.swing.JLabel();
        Reitur10 = new javax.swing.JLabel();
        Reitur9 = new javax.swing.JLabel();
        Reitur5 = new javax.swing.JLabel();
        Reitur6 = new javax.swing.JLabel();
        Reitur7 = new javax.swing.JLabel();
        Reitur8 = new javax.swing.JLabel();
        Reitur4 = new javax.swing.JLabel();
        Reitur3 = new javax.swing.JLabel();
        Reitur2 = new javax.swing.JLabel();
        Reitur1 = new javax.swing.JLabel();
        map = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jSettings = new javax.swing.JMenu();
        soundOn = new javax.swing.JCheckBoxMenuItem();
        newGame = new javax.swing.JMenuItem();
        jInfo = new javax.swing.JMenu();
        instructionsBtn = new javax.swing.JMenuItem();
        aboutBtn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setForeground(new java.awt.Color(153, 204, 255));
        setMaximumSize(new java.awt.Dimension(657, 592));
        setMinimumSize(new java.awt.Dimension(657, 592));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        inventoryPanel.setBackground(new java.awt.Color(255, 255, 255));
        inventoryPanel.setMaximumSize(new java.awt.Dimension(460, 84));
        inventoryPanel.setMinimumSize(new java.awt.Dimension(460, 84));
        inventoryPanel.setOpaque(false);
        inventoryPanel.setPreferredSize(new java.awt.Dimension(460, 84));
        inventoryPanel.setLayout(new java.awt.GridLayout(1, 0));

        slot1.setBackground(new java.awt.Color(255, 255, 204));
        slot1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/is/hi/Lokaverkefni/vidmot/ballsIcon.png"))); // NOI18N
        slot1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        slot1.setMaximumSize(new java.awt.Dimension(92, 84));
        slot1.setMinimumSize(new java.awt.Dimension(92, 84));
        slot1.setName(""); // NOI18N
        slot1.setOpaque(true);
        slot1.setPreferredSize(new java.awt.Dimension(92, 84));
        inventoryPanel.add(slot1);

        slot2.setBackground(new java.awt.Color(255, 255, 204));
        slot2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        slot2.setMaximumSize(new java.awt.Dimension(92, 84));
        slot2.setMinimumSize(new java.awt.Dimension(92, 84));
        slot2.setName(""); // NOI18N
        slot2.setOpaque(true);
        slot2.setPreferredSize(new java.awt.Dimension(92, 84));
        inventoryPanel.add(slot2);

        slot3.setBackground(new java.awt.Color(255, 255, 204));
        slot3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        slot3.setMaximumSize(new java.awt.Dimension(92, 84));
        slot3.setMinimumSize(new java.awt.Dimension(92, 84));
        slot3.setName(""); // NOI18N
        slot3.setOpaque(true);
        slot3.setPreferredSize(new java.awt.Dimension(92, 84));
        inventoryPanel.add(slot3);

        slot4.setBackground(new java.awt.Color(255, 255, 204));
        slot4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        slot4.setMaximumSize(new java.awt.Dimension(92, 84));
        slot4.setMinimumSize(new java.awt.Dimension(92, 84));
        slot4.setName(""); // NOI18N
        slot4.setOpaque(true);
        slot4.setPreferredSize(new java.awt.Dimension(92, 84));
        inventoryPanel.add(slot4);

        slot5.setBackground(new java.awt.Color(255, 255, 204));
        slot5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        slot5.setMaximumSize(new java.awt.Dimension(92, 84));
        slot5.setMinimumSize(new java.awt.Dimension(92, 84));
        slot5.setName(""); // NOI18N
        slot5.setOpaque(true);
        slot5.setPreferredSize(new java.awt.Dimension(92, 84));
        inventoryPanel.add(slot5);

        charPort.setBackground(new java.awt.Color(255, 255, 255));
        charPort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charPort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/is/hi/Lokaverkefni/vidmot/portrait.png"))); // NOI18N
        charPort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        charPort.setMaximumSize(new java.awt.Dimension(85, 122));
        charPort.setMinimumSize(new java.awt.Dimension(85, 122));
        charPort.setName(""); // NOI18N
        charPort.setOpaque(true);
        charPort.setPreferredSize(new java.awt.Dimension(85, 122));

        inspect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/is/hi/Lokaverkefni/vidmot/inspectIcon.png"))); // NOI18N
        inspect.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inspect.setMaximumSize(new java.awt.Dimension(92, 84));
        inspect.setMinimumSize(new java.awt.Dimension(92, 84));
        inspect.setPreferredSize(new java.awt.Dimension(92, 84));
        inspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectActionPerformed(evt);
            }
        });

        area.setBackground(new java.awt.Color(255, 255, 204));
        area.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        area.setText("The Town of Flabbergast");
        area.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        area.setOpaque(true);

        mapPanel.setBackground(new java.awt.Color(153, 204, 255));
        mapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        mapPanel.setMaximumSize(new java.awt.Dimension(640, 420));
        mapPanel.setMinimumSize(new java.awt.Dimension(640, 420));
        mapPanel.setPreferredSize(new java.awt.Dimension(640, 420));
        mapPanel.setRequestFocusEnabled(false);
        mapPanel.setLayout(null);

        Reitur12.setBackground(new java.awt.Color(0, 0, 0));
        Reitur12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur12.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur12.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur12.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur12);
        Reitur12.setBounds(0, 0, 160, 140);

        Reitur11.setBackground(new java.awt.Color(0, 0, 0));
        Reitur11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur11.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur11.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur11.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur11);
        Reitur11.setBounds(160, 0, 160, 140);

        Reitur10.setBackground(new java.awt.Color(0, 0, 0));
        Reitur10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur10.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur10.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur10.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur10);
        Reitur10.setBounds(320, 0, 160, 140);

        Reitur9.setBackground(new java.awt.Color(0, 0, 0));
        Reitur9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur9.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur9.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur9.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur9);
        Reitur9.setBounds(480, 0, 160, 140);

        Reitur5.setBackground(new java.awt.Color(0, 0, 0));
        Reitur5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur5.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur5.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur5.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur5);
        Reitur5.setBounds(480, 140, 160, 140);

        Reitur6.setBackground(new java.awt.Color(0, 0, 0));
        Reitur6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur6.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur6.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur6.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur6);
        Reitur6.setBounds(320, 140, 160, 140);

        Reitur7.setBackground(new java.awt.Color(0, 0, 0));
        Reitur7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur7.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur7.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur7.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur7);
        Reitur7.setBounds(160, 140, 160, 140);

        Reitur8.setBackground(new java.awt.Color(0, 0, 0));
        Reitur8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur8.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur8.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur8.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur8);
        Reitur8.setBounds(0, 140, 160, 140);

        Reitur4.setBackground(new java.awt.Color(0, 0, 0));
        Reitur4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur4.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur4.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur4.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur4);
        Reitur4.setBounds(0, 280, 160, 140);

        Reitur3.setBackground(new java.awt.Color(0, 0, 0));
        Reitur3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur3.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur3.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur3.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur3);
        Reitur3.setBounds(160, 280, 160, 140);

        Reitur2.setBackground(new java.awt.Color(0, 0, 0));
        Reitur2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur2.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur2.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur2.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur2);
        Reitur2.setBounds(320, 280, 160, 140);

        Reitur1.setBackground(new java.awt.Color(0, 0, 0));
        Reitur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reitur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/is/hi/Lokaverkefni/vidmot/char.png"))); // NOI18N
        Reitur1.setMaximumSize(new java.awt.Dimension(34, 14));
        Reitur1.setMinimumSize(new java.awt.Dimension(34, 14));
        Reitur1.setPreferredSize(new java.awt.Dimension(34, 14));
        mapPanel.add(Reitur1);
        Reitur1.setBounds(480, 280, 160, 140);

        map.setIcon(new javax.swing.ImageIcon(getClass().getResource("/is/hi/Lokaverkefni/vidmot/1.png"))); // NOI18N
        map.setText("jLabel1");
        map.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        map.setMaximumSize(new java.awt.Dimension(640, 420));
        map.setMinimumSize(new java.awt.Dimension(640, 420));
        map.setPreferredSize(new java.awt.Dimension(640, 420));
        mapPanel.add(map);
        map.setBounds(0, 0, 640, 420);

        menuBar.setBackground(new java.awt.Color(255, 255, 204));

        jSettings.setText("Settings");

        soundOn.setSelected(true);
        soundOn.setText("Sound On");
        soundOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundOnActionPerformed(evt);
            }
        });
        jSettings.add(soundOn);

        newGame.setText("New Game");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });
        jSettings.add(newGame);

        menuBar.add(jSettings);

        jInfo.setText("Info");

        instructionsBtn.setText("Instructions");
        instructionsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionsBtnActionPerformed(evt);
            }
        });
        jInfo.add(instructionsBtn);

        aboutBtn.setText("About");
        aboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutBtnActionPerformed(evt);
            }
        });
        jInfo.add(aboutBtn);

        menuBar.add(jInfo);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(charPort, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(charPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inspect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(140, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //opens an information window
    private void aboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutBtnActionPerformed
        About about = new About(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_aboutBtnActionPerformed
    //opens an instruction window
    private void instructionsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsBtnActionPerformed
        Instructions instructions = new Instructions(this,true);
        instructions.setVisible(true);
    }//GEN-LAST:event_instructionsBtnActionPerformed
    //checks curent square when inspect button is pressed
    private void inspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectActionPerformed
        checkSquare();
    }//GEN-LAST:event_inspectActionPerformed
    //not used, can't delete
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened
    //toggles sound of sound on is toggled
    private void soundOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundOnActionPerformed
        music.stopMusic();
    }//GEN-LAST:event_soundOnActionPerformed
    //asks for confirmation and starts a new game if yes is pressed
    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        if (JOptionPane.showConfirmDialog(this, "<html>Do you want to start a new Game?</html>", "Sart new game:", JOptionPane.YES_NO_OPTION)==0){
            newGame();
        }
    }//GEN-LAST:event_newGameActionPerformed
    //opens main menu
    private void mainMenu(){
        MainMenu menu = new MainMenu(this,true);
        menu.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JuggleQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JuggleQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JuggleQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JuggleQuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JuggleQuest().setVisible(true);
                
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Reitur1;
    private javax.swing.JLabel Reitur10;
    private javax.swing.JLabel Reitur11;
    private javax.swing.JLabel Reitur12;
    private javax.swing.JLabel Reitur2;
    private javax.swing.JLabel Reitur3;
    private javax.swing.JLabel Reitur4;
    private javax.swing.JLabel Reitur5;
    private javax.swing.JLabel Reitur6;
    private javax.swing.JLabel Reitur7;
    private javax.swing.JLabel Reitur8;
    private javax.swing.JLabel Reitur9;
    private javax.swing.JMenuItem aboutBtn;
    private javax.swing.JLabel area;
    private javax.swing.JLabel charPort;
    private javax.swing.JButton inspect;
    private javax.swing.JMenuItem instructionsBtn;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JMenu jInfo;
    private javax.swing.JMenu jSettings;
    private javax.swing.JLabel map;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newGame;
    private javax.swing.JLabel slot1;
    private javax.swing.JLabel slot2;
    private javax.swing.JLabel slot3;
    private javax.swing.JLabel slot4;
    private javax.swing.JLabel slot5;
    private javax.swing.JCheckBoxMenuItem soundOn;
    // End of variables declaration//GEN-END:variables
}
