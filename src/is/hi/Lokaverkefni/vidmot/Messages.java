/*
 * Kári Snær Kárason - ksk12@hi.is
 * JuggleQuest lokaverkefni - 10/04/2016
 * 
 * Þessi klasi heldur utan um öll þau skilaboð sem eru birt í viðburðum (events)
 * leiksins. Klasinn talar við LeikUpplýsinga klasann til að vita hvað hefur skeð/
 * hvað leikmaður hefur gert til að ákvarða hvað hann getur gert að hverju sinni.
 */
package is.hi.Lokaverkefni.vidmot;

import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kári
 */
public class Messages extends javax.swing.JDialog {
    //array that holds messages
    private String[][] m = new String[50][5];
    //array that holds button prompts
    private String[][] b = new String[50][4];
    //arrays that hold message photos
    private String[] photoS = {"f0.png","f1.png", "f2.png", "f3.png", "f4.png", "f5.png", "f6.png", "f7.png"
    , "f8.png", "f9.png", "f10.png", "f11.png", "f12.png", "f13.png", "f7.png", "f15.png", "f16.png", "f17.png", "f18.png"
    , "f19.png", "f20.png", "f21.png", "f22.png", "f23.png", "f24.png", "f25.png", "f26.png", "f27.png", "f28.png", "f7.png", "s0.png","s1.png", "s2.png", "s3.png"};
    private ImageIcon[] photo = new ImageIcon[photoS.length];

    int s = 0;
    /**
     * Creates new form Messages
     */
    public Messages(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Messages.DO_NOTHING_ON_CLOSE);
        
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        
        for(int i=0; i<photoS.length; i++){
            photo[i] = new ImageIcon(getClass().getResource(photoS[i]));
        }
        messageInfo();

    }
    //holds messages
    public void messageInfo(){
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        
        //at home
        m[30][0] = "<html>Our story starts in the small town of Flabbergast, where a young woman is busy being extremely bored.</html>";
        m[31][0] = "<html>There's not much to do in Flabbergast, so she is amusing herself by practicing her favorite hobby, juggling.<br>Then, suddenly, a letter falls through the letter cute. Lazily, she walks over and picks it up.<br>Her heart skips a beat when she sees The Royal Seal of Revelle on the front. Hastily she rips the letter open and reads it.</html>";
        m[32][0] = "<html>Dear Sir or Madam,<br><br>Laslow, the great king of Revelle, is holding a great talent competition.<br>The grand prize: his daughter's hand in marriage! Should you choose to take part<br>you must be at the castle of Revelle on Sunday evening. This letter is the entry ticket, so make sure to bring it.<br><br>On behalf of King Laslow, Stewart Inga Obbobb</html>";
        m[33][0] = "<html>\"Sunday evening!\" she yells, \"But that's tomorrow. I must hurry to the city and win this competition with my amazing juggling skills!\" She finishes and grabbes her balls (her juggling balls that is).<br><br>And so our journey begins. A heroine, a quest, a princess, and some mad juggling skills!</html>";

        b[30][0] = "<html>Next</html>";
        b[31][0] = "<html>Next</html>";
        b[32][0] = "<html>Next</html>";
        b[33][0] = "<html>Next</html>";
        
        
        //poo in pool
        m[0][0] = "<html>To the side is the local swimming pool where your dad works. The only thing in it right now <br>is a brown shape that could either be a leaf or a piece of poo.</html>";
        m[0][1] = "<html>It's quite squishy. Definitely poop.</html>";
        m[0][2] = "<html>It splits into two smaller pieces of what is obviously feces.</html>";
        m[0][3] = "<html>You practice juggling on the dive board.<br>Your balance has increased!</html>";
        
        b[0][0] = "<html>Poke it with a stick</html>";
        b[0][1] = "<html>Throw a rock at it</html>";
        b[0][2] = "<html>Gross. I'm leaving</html>";
        b[0][3] = "<html>Juggle</html>";

        
        //dad at square
        m[1][0] = "<html>You are now in the town square, where you unfortunately run into your dad.<br> \"Where are you going, little bean?\" he asks.<br>You know that he would most likely not allow you to go on this quest.</html>";
        m[1][1] = "<html>\"Thank you for being so honest with me.\" he says and smiles at you with fatherly love in his eyes.<br> \"Obviously you can't go though!\" he continues and escorts you back home.<br>So this is where your quest ends....at the same place it started.</html>";
        m[1][2] = "<html>\"Hmmm, on a Saturday? Where are you really going?\"</html>";
        if(fg.getInfo(0)) m[1][3] = "<html>\"There's a what in the what?!?\" he yells,<br> and runs off towards the pool.</html>";
       
        if(!fg.getInfo(31)&&!fg.getInfo(1))b[1][0] = "<html>I'm going to Revelle</html>";
        else b[1][0]=null;
        if(!fg.getInfo(31)&&!fg.getInfo(1))b[1][1] = "<html>I'm just going to school</html>";
        else b[1][1]=null;
        if(!fg.getInfo(31))b[1][2] = "<html>Leave the square</html>";
        else b[1][2] = "<html>New game</html>";
        if(fg.getInfo(0)&&!fg.getInfo(31)&&!fg.getInfo(1)) b[1][3] = "<html>There's a poop in the pool!</html>";
        else if(fg.getInfo(31)){ b[1][3]= "<html>Quit</html>";}
        else b[1][3]= null;
        
        //butcher
        m[2][0] = "<html>The last house in town is the local butcher. You've never really liked him<br>as you suspect him in the sudden dissapearence of your old cat, Danger.</html>";
        m[2][1] = "<html>You furiously bang on the door and run behind a nearby rock.<br>After a few seconds the butcher opens the door, nervously buckling his belt.</html>";
        m[2][2] = "<html>It was such a good cat. It got it's name right after birth, when it immediately crawled away from its mother and got picked up by an eagle. The eagle then accidentally dropped it into a river, where the kitten landed on a large leaf. A passing turtle saw the leaf and ate it in one bite. Thankfully the turtle was allergic to cat hair and spat the kitten out whole. It flew on to the river bank where an old fisherman found it and brought it into town.<br>He let you keep it, and for a long time you two were best friends.</html>";
        if(fg.getInfo(3)) m[2][3] = "<html>You browse for a while, asking an annoying amount of questions and then always saying <br>\"That's not what I heard...\" after the butcher answers.<br>Eventually you buy a nice piece of steak.</html>";
        else m[2][3] = "<html>You juggle as loudly as you can outside the butcher's window.<br>This mostly involves juggling nomally while yelling a lot.</html>";
        
        b[2][0] = "<html>Knock on his door and hide<html>";
        b[2][1] = "<html>Think about Danger<html>";
        b[2][2] = "<html>Venture forth!<html>";
        if(fg.getInfo(3)) b[2][3] = "<html>Buy some meat<html>";
        else b[2][3] = "Juggle";
        
        //River
        m[3][0] = "<html>You have reached Frog River, named that because it doesn't have any frogs in it.<br>It was named by Sir Bob Flanigan, a famous explorer and bad Namer-of-Things.<html>";
        m[3][1] = "<html>You start daydreaming about your future life as the juggling world champion. It is glorious.</html>";
        m[3][2] = "<html>You get down on your knees and drink the river water.<br>You get a strong taste of dirt with just a hint of rock.</html>";
        m[3][3] = "<html>You juggle while looking at your reflection in the water.<br>Your technique has improved!</html>";
        
        b[3][0] = "<html>Gaze at the river</html>";
        b[3][1] = "<html>Take a sip</html>";
        b[3][2] = "<html>Walk along the river</html>";
        b[3][3] = "<html>Juggle</html>";
        
        //flowers
        m[4][0] = "<html>You see a group of beautiful flowers in front of you, they're the same shade of purple as a duck, if ducks were purple.<html>";
        m[4][1] = "<html>You take a strong wiff of the flowers. You immediately feel light headed and lie down on the ground.<br>Two hours later you wake up, feeling strangely refreshed.</html>";
        m[4][2] = "<html>You gently take some petals off of one of the flowers and place them in your backpack. </html>";
        m[4][3] = "<html>You perform one of your signature tricks.<br>The flowers are not impressed.</html>";
        
        b[4][0] = "<html>Smell flowers</html>";
        b[4][1] = "<html>Pick a flower</html>";
        b[4][2] = "<html>Keep walking</html>";
        b[4][3] = "<html>Juggle</html>";
        
        //ogre
        m[5][0] = "<html>Before you is the bridge that crosses Frog River. But a mighty ogre stands in front of it.<br>He doesn't look happy to see you, and is growling loudly.<br>After a while you realize it is actually his stomache that is growling. He appears to be hungry.</html>";
        m[5][1] = "<html>\"Dear sir.\" you say, \"Would you be so kind as to grant me leave to stroll over yonder bridge?\"<br><br>The ogre scratches his butt, \"Me want meat!\" he says, \"Come here and be meat!\".</html>";
        m[5][2] = "<html>You rush the ogre, while yelling at the top of your lungs.<br>The ogre grabs you by the hair, swings you around and thorws you into the air.<br>Amazingly you manage to survive....right until you land and become a human puddle.<br>So this is where your quest ends. By fighting a huge ogre with no weapon. Could have seen it coming.</html>";
        if(fg.getInfo(2)&&fg.getInfo(4))m[5][3] = "<html>In a genius move, you stuff the flowerpetals into the meat and throw it at the ogre.<br>He catches it in the air and eats it in one bite. He now wants more meat and is rushing towards you.<br>Just before he reaches you he topples over, fast asleep.</html>";
        
        if(!fg.getInfo(30)&&!fg.getInfo(6)){ b[5][0] = "<html>Talk to the ogre</html>";}
        else{ b[5][0]=null;}
        if(!fg.getInfo(30)&&!fg.getInfo(6)){ b[5][1] = "<html>Fight the ogre!</html>";}
        else{ b[5][1]=null;}
        if(!fg.getInfo(30)){ b[5][2] = "<html>Go</html>";}
        else{ b[5][2] = "<html>New Game</html>";}
        if(fg.getInfo(2)&&fg.getInfo(4)&&!fg.getInfo(30)){b[5][3] = "<html>Combine flower & meat</html>";}
        else if(fg.getInfo(30)){ b[5][3]= "<html>Quit</html>";}
        else b[5][3]= null;
        
        //forrest
        m[6][0] = "<html>You have just entered The Forrest of Many Spooks.<br>Surprisingly, not a very popular tourist destination.</html>";
        m[6][1] = "<html>You look around for a good tree to climb.<br>Unfortunately all the branches are too high for you to reach.<br>As you give up you think you hear a nearby squirrel laughing.</html>";
        m[6][2] = "<html>Your grandmother told you about magical cool things that could be found in the forrest.<br>You step into the undergrowth and look for a long time, but the only half interesting thing you find is bear poo in the shape of a pinecone, alternatively it could just be a big pinecone. Either way, sorta cool.</html>";
        m[6][3] = "<html>You juggle in the dim light of the forrest, concentrating mostly on the sound of the balls in the air.<br>Your ball awareness has increased!</html>";
        
        b[6][0] = "<html>Climb a tree</html>";
        b[6][1] = "<html>Look for cool things</html>";
        b[6][2] = "<html>Walk along</html>";
        b[6][3] = "<html>Juggle</html>";
        
        //elf 1
        m[7][0] = "<html>Suddenly, a mysterious creature appears before you.<br>\"I am one of the guardians of this forrest!\" it proclaimes,<br> \"If you wish to venture further you must answer my riddle, human child.\"<br>You say nothing, and after an uncomfortable silence the guardian continues:<br>\"Is there not not not an even number of not's in this sentence?\"</html>";
        m[7][1] = "<html>\"That isn't not incorrect, I'm afraid\" he says.<br>You can't see his mouth, but you're sure he's smiling smugly.</html>";
        m[7][2] = "<html>\"That isn't not correct!\" he says, \"You don't not have permission to continue.\"</html>";
        m[7][3] = "<html>You can see his eyes following the balls in the air.<br>\"I do not see the point of that action.\" he says.<br>You are at first annoyed, but then remember that not everyone can enjoy the same things in life.<br>Your ability to take criticism has increased!<br></html>";
        
        if(!fg.getInfo(8))b[7][0] = "<html>Yes</html>";
        else b[7][0]=null;
        if(!fg.getInfo(8))b[7][1] = "<html>No</html>";
        else b[7][1]=null;
        if(!fg.getInfo(8))b[7][2] = "<html>Go think about it</html>";
        else b[7][2]= "<html>Go</html>";
        b[7][3] = "<html>Juggle</html>";
        
        //elf 2
        m[8][0] = "<html>\"Heyyy, maaaan,\" You hear from a nearby tree. There stands another guardian.<br> \"Like, what's up dude?\" he continues as a gray bony hand rises from his cloak and waves at you. </html>";
        m[8][1] = "<html>\"Alright!\" he says, \"So rad to meet friendly creatures, bears can be so moody, you know?\" He puts his hand back in his cloak and leans up against a tree.<br>\"You know, I'm supposed to ask you a riddle to, like, protect the integrity of the forrest or whatever. But aren't we all part of the forrest, you know?\" he says and stares at the tree.<br>\"We are one.\" he whispers after a while. You are unsure if he was addressing you or the tree.</html>";
        m[8][2] = "<html>\"Awww, maaaan\" he says sadly, \"Don't be such a downer! Show me some love.\"<br>He continues to wave, but with a little less enthusiasm than before.</html>";
        m[8][3] = "<html>On the spot you invent a new trick, incorporating a wave motion into the ball throw.<br>\"Radical!\" he yells, \"Thats, like, THE coolest thing I've seen since I witnessed a squirrel do a backflip!\"<br> \"You can go wherever you want, dude. Just spread your talent. Like butter on bread. The world is your bread! You are butter!\"</html>";
        
        b[8][0] = "<html>Wave back</html>";
        b[8][1] = "<html>Look dissaprovingly</html>";
        b[8][2] = "<html>Leave</html>";
        b[8][3] = "<html>Juggle</html>";
        
        //elf 3
        m[9][0] = "<html>A third guardians swoops in front of you.<br>\"Hello, human.\" he says in a monotone voice, \"I am the oldest and wisest guardian. And....I ask the hardest riddles!\"<br>He walks a little closer. \"I am as old as my middle brother plus half my youngest brother's age. In 200 years, my youngest brother will be twice as old, and I will be as old as him plus half my middle brother's age. How old am I now?\"</html>";
        m[9][1] = "<html>\"I'm afraid not, little one. I guess that one was too hard for a human mind.\"</html>";
        m[9][2] = "<html>You got it. I'm 500 years old, although I'm not looking a day older then 300 obviously. Be on your way.</html>";
        m[9][3] = "<html>\"I'm afraid not, little one. I guess that one was too hard for a human mind.\"</html>";
        
        if(!fg.getInfo(10)) b[9][0] = "<html>700</html>";
        else b[9][0]= null;
        if(!fg.getInfo(10)) b[9][1] = "<html>500</html>";
        else b[9][1]= null;
        b[9][2] = "<html>Go away</html>";
        if(!fg.getInfo(10)) b[9][3] = "<html>600</html>";
        else b[9][3]= null;
        
        //Village man 1
        m[10][0] = "<html>As you emerge from the forrest, you find yourself in a small village made mostly of stone.<br> You can see some people in the distance at what seems to be the village square.<br>As you approach a man emerges from a nearby house. \"Hello traveler, and welcome to Stonehaven.\" he says and extends his hand towards you.</html>";
        m[10][1] = "<html>You shake the man's hand. It feels very cold to the touch.<br>\"It's not often we get visitors here. It's so nice to meet new people.\" he says and smiles.<br>After a while of him gripping your hand you pull it back. It still feels very cold.</html>";
        m[10][2] = "<html>\"Where are the rest of the villagers?\" you ask, looking around.<br>\"Am I not enough for you?\" he asks and looks at you, hand still outstretched.</html>";
        m[10][3] = "<html>You show the man your juggling skills.<br>\"Oh, such movement. Your hands are very fluid, aren't they?\" he says and extends both hands towards you, \"Can I see them?\"</html>";
        
        b[10][0] = "<html>Take his hand</html>";
        b[10][1] = "<html>Talk to him</html>";
        b[10][2] = "<html>Walk on</html>";
        b[10][3] = "<html>Juggle</html>";
        
        //Village man 2
        if(fg.getInfo(14)) m[11][0] = "<html>As you reach the village square you freeze in place. It is filled with stone statues, completely life-like, except for the fact that they're made of stone and not human flesh. You hear footsteps behind you. \"Stunning, aren't they?\" the man with the cold hands says, \"They are my dear friends and family. And now you will join them my dear, fluid no more.\" As he says this you realize your hand is even colder than before. You look down and see to your horror that it has turned to stone.</html>";
        else m[11][0] = "<html>As you reach the village square you freeze in place. It is filled with stone statues, completely life-like, except for the fact that they're made of stone and not human flesh. You hear footsteps behind you. \"It's rude not to take someones hand, you know?\" the strange man from before says and grimaces at you, \"These are my friends and family, we live here together. And now you must as well!\" he yells and runs at you, hands extended.</html>";
        if(fg.getInfo(14)) m[11][1] = "<html>You swing your stone fist as hard as you can, which turns out to be pretty hard. As your fist of fury connects with his face he goes limp and falls to the ground. As soon as he does you can feel your hand returning to normal. You don't dare touch him to check for a pulse, but you think he might be dead. Sadly, the other people in the village don't seem to be returning to normal.</html>";
        else m[11][1] = "<html>You run over to the closest statue. \"If you don't let me go l'll break it!\" you say, and just barely manage to lean the statue over a little bit. \"Don't you dare hurt them!\" he screams and lunges towards you. You back away, toppling the statue over in the process. \"Nooooo!\" the man cries out and throws himself under the statue. He is crushed under it's weight. You highly doubt he survived.</html>";
        if(fg.getInfo(14)) m[11][2] = "<html>As you turn to run you can hear the man laughing. \"You won't get far.\" he says and makes no effort to go after you. As you reach the other end of the square your feet stop moving, and you fall down on the ground. You can feel an immense cold creeping along your hands and feet. In a few minutes you are completely engulfed in stone.</html>";
        else m[11][2] = "<html>You try to convince him not to turn you into stone, but he doesn't appear to be listening to you. He is getting closer.</html>";
        if(fg.getInfo(14)) m[11][3] = "<html>You grab the flute the elf gave you. As you blow into it a strange sound emerges. \"Mandaloooooo!\" the flute seems to cry out. As soon as it finishes it cry the guardians appear out of seemingly nowhere. They all attack the man simutaneously, leaving him motionless on the ground. \"The call has been answered.\" the oldest guardian says, and then they are gone, as quickly as they appeared. Thankfully, the stone on your hand has also dissapeared.</html>";
        else m[11][3] = "<html>You grab the flute the elf gave you. As you blow into it a strange sound emerges. \"Mandaloooooo!\" the flute seems to cry out. As soon as it finishes it cry the guardians appear out of seemingly nowhere. They all attack the man simutaneously, leaving him motionless on the ground. \"The call has been answered.\" the oldest guardian says, and then they are gone, as quickly as they appeared.</html>";
        
        if(fg.getInfo(14)&&!fg.getInfo(32)) b[11][0] = "<html>Punch him</html>";
        else if(!fg.getInfo(32)) b[11][0] = "<html>Threaten statue</html>";
        if(fg.getInfo(33)||fg.getInfo(32)) b[11][0] = null;
        if(fg.getInfo(14)&&!fg.getInfo(32)) b[11][1] = "<html>Run away</html>";
        else if(!fg.getInfo(32)) b[11][1] = "<html>Convince him</html>";
        if(fg.getInfo(33)||fg.getInfo(32)) b[11][1]=null;
        if(fg.getInfo(32)){ b[11][2] = "<html>New game</html>"; jBtn3.setVisible(true);}
        if(fg.getInfo(33)){ b[11][2] = "<html>Leave this madness</html>"; jBtn3.setVisible(true);}
        if(fg.getInfo(13)&&!fg.getInfo(32)) b[11][3] = "<html>Play flute</html>";
        else if(fg.getInfo(32)){ b[11][3] = "<html>Quit</html>";}
        if(fg.getInfo(33)) b[11][3] = null;
        //Mountains
        m[12][0] = "<html>Before you rises a large and majestic mountain range. A sign to your sides tells you they are called: The Gray Mountains.<br>In a humorous turn of events, someone has scribbled over the r.</html>";
        m[12][1] = "<html>You sing a song that your grandmother taught you many years ago. Suprising yourself by the fact that you still know every word. As the song rises into a sad and beautiful chorus, a nearby group of mountain goats decide to join in, resulting in a harmony the world will never hear again. A single tear drops down your cheek. \"That one was for you, grandma.\" you whisper after the song ends.</html>";
        m[12][2] = "<html>After walking for a long time, you certainly have earned a rest.<br>You sit down at the base of the mountain, thinking back on what you've been through up until now. The ferocious Ogre. The mysterious guardians. The things classical adventures are made out of. But what rises to your mind most strongly are the people in the village. You managed to escape the stony embrace of that madman, but they are stuck like that forever. How long had they been like that? Only the stone knew now.</html>";
        m[12][3] = "<html>You perform a basic juggling routine while pondering how small you are compared to the mountains.<br>Your sense of your own place in the Universe has increased!</html>";
        
        b[12][0] = "<html>Sing</html>";
        b[12][1] = "<html>Take a rest</html>";
        b[12][2] = "<html>Walk on</html>";
        b[12][3] = "<html>Juggle</html>";
        
        //bells
        if(!fg.getInfo(17))m[13][0] = "<html>You come upon a strange bell hanging from a stand in the middle of the mountain range.<br>You can't see any other sign of human life around.</html>";
        else m[13][0] = "<html>\"Can't get enough of my prophecies, eh?\" the old woman says.</html>";
        if(!fg.getInfo(17))m[13][1] = "<html>You grab the string hanging from the ball and give it a good yank. A few moment later a hatch, that had been hidden with dirt, opens a short distance behind the bell. You can hear grunting and panting from the hole, and after a while an old woman emerges, looking quite tired. \"Oh, hello there.\" she says, inbetween breaths, \"Have you come for a prophecy?\"</html>";
        else m[13][1] = "<html>\"One second.\" she says. Right after she says this her eyes roll back into her head, so all you can see are the whites. She starts humming loudly, \"OMMMMMMMMMMM, OMMMMMMMMMM.\"<br>Suddenly her eyes snap back to you. \"I see.... a beast man you shall face, and a man with great contact to the ground. History shall fly by and emotions pulled like strings before you face the final challenge.\"<br>The woman coughs, \"Well, I hope that helps you!\" </html>";
        if(!fg.getInfo(17))m[13][2] = "<html>It is a beautiful yellow. It seems untouched by the harshness of weather and time.</html>";
        else m[13][2] = "<html>\"A prophecy. A cryptic message that will undoubtedly become meaningful later in the story.... the story of your life that is.\"</html>";
        if(!fg.getInfo(17))m[13][3] = "<html>You sit down below the bell and juggle over it, careful not to touch it or the stand.<br>Your precision has increased!</html>";
        else m[13][3] = "<html>You perform a half handed supertwist expertly.<br>\"I knew you were going to do that.\" the old woman says, unimpressed.</html>";
        
        if(!fg.getInfo(17))b[13][0] = "<html>Ring bell</html>";
        else b[13][0]= "<html>Ask for prophecy</html>";
        if(!fg.getInfo(17))b[13][1] = "<html>Inspect bell</html>";
        else b[13][1] = "<html>A what?</html>";
        b[13][2] = "<html>Leave</html>";
        b[13][3] = "<html>Juggle</html>";
        
        //waterfall
        m[14][0] = "<html></html>";
        m[14][1] = "<html></html>";
        m[14][2] = "<html></html>";
        m[14][3] = "<html></html>";
        
        b[14][0] = "<html></html>";
        b[14][1] = "<html></html>";
        b[14][2] = "<html></html>";
        b[14][3] = "<html>Juggle</html>";
        
        //moat
        m[15][0] = "<html>You have almost made it to Revelle! But between you and the city is a massive moat. Oh, and it's filled with sharks.</html>";
        m[15][1] = "<html>So you jump into the water, that I already told you was filled with sharks. The things with big teeth. That thing that's been known to eat boats.<br>As soon as you hit the water you start swimming. All around you fins start closing in. When you're half way there the are upon you!<br>Huh, that's strange. You're not dead. The only thing you can feel are light nudges. You submerge yourself and see that what looked like sharks are infact turtles with fins glued to their backs. You make it safely across, and even get some help from your new turtle friends.</html>";
        m[15][2] = "<html>You practice real hard. Who know's how many more chances you'll get before the competition.</html>";
        if(fg.getInfo(13)) m[15][3]= "<html>You blow the flute the guardians gave you, it gives out a strange cry, \"Mandalooooo!\" it yells out. Instantly, the three guardians appear. \"What is the danger?\" they ask. \"Ummm, could you give me a lift over this moat. I have a talent competition to win.\" you say. \"What a thrilling scenario....\" the oldest guardian says, \"Borkley, can you deal with this?\" he says to the guardian that waved at you. \"Sure thing, bro!\" he says and swoops you into his arms and flies you over the moat. \"Farwell, little dudette, and good luck on your quest!\" he says and then dissapears along with his brothers. </html>";
         
        
        b[15][0] = "<html>Swim over</html>";
        b[15][1] = "<html>Juggle</html>";
        b[15][2] = "<html>Walk on</html>";
        if(fg.getInfo(13)) b[15][3]= "<html>Use flute</html>";
        
        
        //moat crossing
        m[16][0] = "<html>You have reached the moat crossing, but the gate is down. On the other side stands a guard.<br>\"What is your business in Revelle?\" he asks.</html>";
        if(fg.getInfo(19)) m[16][1] = "<html>\"I am here to take part in the talent competition!\" you proclaim. <br>\"Excellent.\" he says, \"I'll just need to see the invitation letter.<br>\"Certainly.\" you say and show him the letter.<br>\"Great, I'll lower the bridge.\"</html>";
        else m[16][1] = "<html>\"I am here to take part in the talent competition!\" you proclaim. <br>\"Excellent.\" he says, \"I'll just need to see the invitation letter.<br>\"Certainly.\" you say and start patting your pockets that you suddenly realize is lying on the floor, in your house, where you dropped it. \"I-I'm afraid I forgot it at home.\"<br>\"Well, then I'm not lowering this bridge.\"</html>";
        m[16][2] = "<html>You look down at the moat. The sharks seem to be moving awfully slow, judging by the movement of the fins. Also, there's a turtle sitting on a rock. Curiously, there is a dislodged shark fin lying next to the turtle. How could a shark lose its fin?</html>";
        if(!fg.getInfo(18)) m[16][3] = "<html>You juggle your very best. If your amazing skills won't make him put the gate down nothing will!<br>\"Ummm, that's nice.\" he says, \"But did you misunderstand the question? I said: What's your business in Revelle?\"<br>Well, hopefully something else will work...</html>";
        else m[16][3]= "<html>You juggle with one hand, just for a laugh.<br>\"Wow, are you gonna do that in the talent competition?\" he says, visibly impressed, \"That's fantastic!\"</html>";
        
        if(!fg.getInfo(18))b[16][0] = "<html>Show letter</html>";
        else b[16][0]= null;
        b[16][1] = "<html>Inspect moat</html>";
        b[16][2] = "<html>Leave</html>";
        b[16][3] = "<html>Juggle</html>";
        
        //Revelle
        m[17][0] = "<html>This is it. You've made it to Revelle! On the other end of the city awaits your destiny. All that is left to do is get there and show these Revellians the graceful and beautiful art of throwing balls in the air.</html>";
        m[17][1] = "<html>You suddenly realize you haven't eaten anything since leaving Flabbergast. You find a cart selling street food and buy a fried squirrel on a stick.<br> It's delicious and nourishing!</html>";
        m[17][2] = "<html>\"Alright, listen you!\" you say to yourself, \"You're gonna go in there and you're gonna give 'em all you've got! You were born to win this competition, you're the best goddamn juggler this land has ever seen!\"<br>You finish by giving yourself a high-five.<br>You are considerably more pepped than before!</html>";
        m[17][3] = "<html>Speaking of throwing balls in the air. You practice one of your harder routines, and at the same time try to stretch your arms and body.<br>Your flexibility has increased!</html>";
        
        b[17][0] = "<html>Buy food</html>";
        b[17][1] = "<html>Pepp talk</html>";
        b[17][2] = "<html>Walk on</html>";
        b[17][3] = "<html>Juggle</html>";
        
        //Revelle crowd
        m[18][0] = "<html>You come upon a group of people. There seems to be great excitement in Revelle because of the competition. A woman from the group sees you and smiles. \"Aaaah, do I spot another competitor?\" she says and becons you to come closer. \"This will be an exciting day indeed.\"</html>";
        m[18][1] = "<html>\"With you added there are five contestants. The other four are already inside the palace, so you should really hurry. I don't know exactly what their acts are but I saw one man walk in with a cow, so that sould be something cool.\"</html>";
        m[18][2] = "<html>You ask the woman which way you should go to reach the palace.<br>\"Ummm, well, there is only two ways to go in this city. The palace is to the far left.\"</html>";
        m[18][3] = "<html>As you start juggling the crowd cheers. \"Wow!\" the woman exclaimes, \"With those kind of moves you have a good chance of winning.\"<br>Little does she know you were holding back your best moves for the competition.</html>";
        
        b[18][0] = "<html>Ask about contestants</html>";
        b[18][1] = "<html>Ask for directions</html>";
        b[18][2] = "<html>Leave</html>";
        b[18][3] = "<html>Juggle</html>";
        
        //Dad in Revelle
        m[19][0] = "<html>You can now see the kings palace! But something else draws your attention. Leaning against a nearby house and looking at you is none other then your dad. \"Wh-what are you doing here, pop?\" you say, hardly believing your eyes.<br> \"Oh, I wasn't going to miss the competition.\" he says and gives you a big smile. \"After I dealt with the poop situation I was chatting with Frank the butcher when he mentioned you had bought a big steak and then ran off out of town. I also heard someone mention a talent competition happening in Revell. After that I put it all together. And even though I'm not happy about you running off, I realize now that you're old enough to make your own decisions. You're not my little bean anymore.\"</html>";
        m[19][1] = "<html>You throw your arms around your father and squeeze tight. You can feel his big arms wrap around you right back. \"Thanks, dad.\" you say, just barely holding back tears.<br>\"No problem, big bean.\" he says, and is a little less successful at not crying.<br>\"We're gonna have to find me a better nickname.\" you say, and his crying turns into laughter.</html>";
        m[19][2] = "<html>\"Oh, that was easy. I met that old fisherman while he was on his lunch break and offered him a year long pool subscription in exchange for a ride up river. I've actually been here for a while.\"</html>";
        m[19][3] = "<html>It was your dad that gave you the set of juggling balls you're still using to this day. You remember the first trick you learned, a falcon rise, and how excited you were to show it to him. You perform it now again.<br>\"Amazing as always.\" he says, \"I don't know how you do it. The other competitors don't stand a chance.\"</html>";
        
        b[19][0] = "<html>Hug him</html>";
        b[19][1] = "<html>How did you get here?</html>";
        b[19][2] = "<html>Go forth!</html>";
        b[19][3] = "<html>Juggle</html>";
        
        //Finale
        m[20][0] = "<html>You have reached the palace of Revelle. As you walk though the doors you can feel your body tremble with excitement. After a few moments the competition starts. Everyone gathers in a big hall. At one end sits King Laslow, his daughter next to him. Near the edges, onlookers have gathered, and in the middle stand the hopeful competitors. Nervous, you take your place next to them.<br>\"Welcome, brave contestants!\" the king says, rising from his seat, \"The talent competition shall now begin. And, as had been stated, the victor will win my daughter's hand in marriage!\" he finishes and looks towards his daughter, who smiles uncomfortably back.</html>";
        m[21][0] = "<html>And so the competition begins. The first contestant is a stand up comedian. He's not very good. The funniest thing about him are his enormous feet.<br>The second contestant has decided to tell the story of Revelle through the art of dance. As she reaches the climactic scene, The Red Moon Battle, you can see a tear stream down the kings face.</html>";
        m[22][0] = "<html>The third competitor is an old man. As his turn comes up he quickly runs out of the room and returns a moment later with an enormous cow in tow. He proceeds to have the cow to amazing tricks, like jump over hurdles and spin around on its hind legs. For the finale he yells: \"Flip!\" and somehow the cow manages to jump into the air, flip over itself and land. Your last competitor now takes the stage. A young man, carrying a violin. \"I have written a song for the princess.\" he says. And the song is beautiful. After he finishes the entire room applauds and cries out his name.</html>";
        
        
        b[20][0] = "<html>Next</html>";
        b[21][0] = "<html>Next</html>";
        b[22][0] = "<html>Next</html>";
        
        //juggling routine
        m[23][0] = "<html>Finally it is your turn. You walk into the middle of the room and take out your balls. But, to your horror, you can't think of a single trick! Your nervousness has overwhelmed you. You look around the crowd, every pair of eyes focussed on you. You look at the king, who has one eyebrow raised, seemingly sizing you up. You feel like your only option is to just give up, when suddenly...you meet the princesses gaze. She gives you an encouraging smile. Immediately you can feel all your juggling knowledge rush back! Now to do some sick tricks:</html>";
        if(!fg.getInfo(20)){ m[23][1] = "<html>You throw all balls in the air at the same time, jump in the air and land on your hands. When the balls come down you start juggling them, with your feet!<br>The crowd loves it. Time for your second trick:</html>";}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ m[23][1] = "<html>You close your eyes and juggle behind your back, while also tapdancing ferociously.<br>It is immensly crowd pleasing. Time for your final trick!</html>";}
        if(fg.getInfo(20)&&fg.getInfo(21)){ m[23][1] = "<html>You pull out every trick you know, performin one after the other. As the routine goes on you slowly make your way towards the princess, your eyes locked with hers. She stands up and walks to meet you. As you reach her you you launch the balls into the air for your final trick, but before you can finish it she grabs you by the shoulders and draws you in for a kiss. The balls fall down on the floor as you embrace each other, oblivious to the rest of the world for that single moment in time. <br>\"I don't want your hand in marriage as a price.\" you say, ignoring the fact that you haven't actually won, \"But I would like to take you out on a date.\"<br>\"That sounds great.\" she whispers back, \"Where do you want to go?\"<br>\"I was thinking a romantic boat ride. I know a guy.\" </html>";}
        if(fg.getInfo(22)){ jPhoto.setIcon(photo[24]);fg.music.changeMusic(1); fg.music.playMusic();}
        
        if(!fg.getInfo(20)){ m[23][2] = "<html>You turn in circles very fast, all the while juggling the balls, every now and then you throw a ball between your legs and sometimes catch one behind your back.<br>The crowd loves it. Time for your second trick:</html>";}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ m[23][2] = "<html>You start singing a warrior song of an ancient tribe, that you read about in school. You incorporate the movement of the trick to align with the beat of the song.<br>It is immensly crowd pleasing. Time for your final trick!</html>";}

        if(!fg.getInfo(20)){ m[23][3] = "<html>You run around the hall, juggling the balls far into the air, always managing to be in the right place at the right time to catch them. Every now and then you do a roll befora catching a ball.<br>The crowd loves it. Time for your second trick: </html>";}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ m[23][3] = "<html>You throw one ball into the air, then throw another one at it, causing them both to fly higher up together. Finally you throw the last ball, that breaks up the other two. you stretch your hands out and a second later one ball lands in each palm, and the third one lands on your head.<br>It is immensly crowd pleasing. Time for your final trick!</html>";}

        
        if(!fg.getInfo(20)){ b[23][0] = "<html>Hammerhead flux</html>";}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ b[23][0] = "<html>Angry sausage</html>"; System.out.println("Sausage");}
        if(fg.getInfo(20)&&fg.getInfo(21)){ b[23][0] = "<html>Fireworks of love</html>"; jBtn2.setVisible(false); jBtn4.setVisible(false);}
        if(fg.getInfo(22)) b[23][0] = null;
        
        if(!fg.getInfo(20)){ b[23][1] = "<html>Banana screw</html>"; jBtn2.setVisible(true);}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ b[23][1] = "<html>Savage roar</html>";}
        
        if(fg.getInfo(22)){ b[23][2] = "New game"; jBtn3.setVisible(true);}
        
        if(!fg.getInfo(20)){ b[23][3] = "<html>Hyper-swine</html>"; jBtn4.setVisible(true);}
        if(!fg.getInfo(21)&&fg.getInfo(20)){ b[23][3] = "<html>Eagle march</html>";}
        if(fg.getInfo(22)){ b[23][3] = "Quit"; jBtn4.setVisible(true);}

        
        
        //man in boat
        m[25][0] = "<html>Out on the river you can see a man in a row boat.<br>He seems to be fishing with a large net.</html>";
        m[25][1] = "<html>You call out to the man, \"Hey there! Could you maybe give me a ride over the river?\"<br>He looks at you blankly, \"What?\" he says.<br>\"I said, could you give me a ride over the river!\" you call a little louder.<br>\"I'm afraid I left my hearing aids at home.\" the man says, \"I can't hear a word you're saying.\"</html>";
        m[25][2] = "<html>You think about the first person to sail on a piece of wood, and the first person to decide to go over the entire ocean on one. What if it had just gone on forever? That person was willing to take that change, and the world was never the same...</html>";
        m[25][3] = "<html>You juggle elegantly, throwing the balls in a perfect arc each time.<br>\"Why are you flailing your hands about like that?\" he yells.<br>It's quite possible that he is mostly blind.</html>";
        
        b[25][0] = "<html>Ask for ride</html>";
        b[25][1] = "<html>Think about boats</html>";
        b[25][2] = "<html>Leave</html>";
        b[25][3] = "<html>Juggle</html>";
        
        //bear trap
        m[26][0] = "<html>Oh no, you've stepped in a bear trap!<br>Thankfully you're not a bear so it doesn't work.</html>";
        m[26][1] = "<html>You try to lift the trap with all your might. Which turns out to be quite a low amount of might.<br>Juggling doesn't really build huge muscles unfortunately.</html>";
        m[26][2] = "<html>The craftmanship is quite impressive....probably.<br>You never really paid attention in shop class.</html>";
        m[26][3] = "<html>You juggle while standing on the trap.<br>You know that as long as your juggling is unBEARably cool, you'll be okay.<br>Your daring has increased!</html>";
        
        b[26][0] = "<html>Take the trap</html>";
        b[26][1] = "<html>Inspect the trap</html>";
        b[26][2] = "<html>Leave</html>";
        b[26][3] = "<html>Juggle</html>";
        
        //flute
        m[27][0] = "<html>As you reach the end of the forrest the last guardian appears before you again.<br>\"Since you managed to answer all our riddles, and even managed to answer mine on your first try, I will give you this.\" he says and hands you a small flute, \"This instrument can be played at any time to get out of trouble. Use it wisely.\"</html>";
        m[27][1] = "<html>\"No need to thank me, human. We reward those with great knowledge.\"<br>Despite his modestly you think you can see a smile under his hood.</html>";
        m[27][2] = "<html>It is made out of a type of wood you do not recognize.<br>It has mysterious symbols carved into it.<br>It smells like forrest berries.</html>";
        m[27][3] = "<html>As soon as you blow into the flute it gives out a strange sound: \"Mandaloooo!\" it cries out.<br>Instantly the other two guardians appear. \"Where's the danger?\" the first one says and looks around.<br>\"No danger.\" the third one replies,\"This idiot just wasted her only use of the flute.\"<br>\"Like, not cool, man.\" the second one says as they all dissapear into the forrest.</html>";
                
        b[27][0] = "<html>Thank him</html>";
        b[27][1] = "<html>Inspect the flute</html>";
        b[27][2] = "<html>Leave</html>";
        b[27][3] = "<html>Play flute</html>";
        
        //letter
        if(fg.getInfo(18))m[28][0] = "<html>There the letter is. Lying on the ground where you left it. How could you have forgotten it?</html>";
        else m[28][0]= "<html>The letter is still lying on the ground where you left it. Good thing you didn't forget to take it with you!</html>";
        m[28][1] = "<html>You pick the letter up and stuff it into your pocket.</html>";
        m[28][2] = "<html>Dear Sir or Madam,<br><br>Laslow, the great king of Revelle, is holding a great talent competition.<br>The grand prize: his daughter's hand in marriage! Should you choose to take part<br>you must be at the castle of Revelle on Sunday evening. This letter is the entry ticket, so make sure to bring it.<br><br>On behalf of King Laslow, Stewart Inga Obbobb</html>";
        
        if(!fg.getInfo(19))b[28][0] = "<html>Take letter</html>";
        else b[28][0]=null;
        b[28][1] = "<html>Read letter again</html>";
        b[28][2] = "<html>Leave</html>";
        
    }
    //shows correct message on dialog
    public void changeInfo(int x){
        if(m[x][0]==null) this.setVisible(false);
        System.out.println(m[x][0]);
        
        if(x<photoS.length) jPhoto.setIcon(photo[x]);
        s=x; 
        
        setButtons(0);   
    }
    //changes text/button info
    private void setButtons(int x){
        jText.setText(m[s][x]);
        messageInfo();
                   
        if(b[s][0]!=null)jBtn1.setText(b[s][0]);
        else jBtn1.setVisible(false);
        
        if(b[s][1]!=null)jBtn2.setText(b[s][1]);
        else jBtn2.setVisible(false);
        
        if(b[s][2]!=null)jBtn3.setText(b[s][2]);
        else jBtn3.setVisible(false);
        
        if(b[s][3]!=null)jBtn4.setText(b[s][3]);
        else jBtn4.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgPanel = new javax.swing.JPanel();
        jText = new javax.swing.JLabel();
        jPhoto = new javax.swing.JLabel();
        jBtn1 = new javax.swing.JButton();
        jBtn2 = new javax.swing.JButton();
        jBtn3 = new javax.swing.JButton();
        jBtn4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(73, 50));
        setMaximumSize(new java.awt.Dimension(522, 414));
        setMinimumSize(new java.awt.Dimension(522, 414));
        setUndecorated(true);
        setResizable(false);

        msgPanel.setBackground(new java.awt.Color(255, 255, 204));
        msgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        msgPanel.setMaximumSize(new java.awt.Dimension(520, 414));
        msgPanel.setMinimumSize(new java.awt.Dimension(520, 414));
        msgPanel.setPreferredSize(new java.awt.Dimension(520, 414));

        jText.setBackground(new java.awt.Color(255, 255, 255));
        jText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jText.setText("Texti");
        jText.setToolTipText("");
        jText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jText.setMaximumSize(new java.awt.Dimension(500, 118));
        jText.setMinimumSize(new java.awt.Dimension(500, 118));
        jText.setOpaque(true);
        jText.setPreferredSize(new java.awt.Dimension(500, 118));

        jPhoto.setBackground(new java.awt.Color(255, 255, 255));
        jPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPhoto.setMaximumSize(new java.awt.Dimension(500, 209));
        jPhoto.setMinimumSize(new java.awt.Dimension(500, 209));
        jPhoto.setOpaque(true);
        jPhoto.setPreferredSize(new java.awt.Dimension(500, 209));

        jBtn1.setText("1");
        jBtn1.setFocusPainted(false);
        jBtn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtn1.setMaximumSize(new java.awt.Dimension(220, 23));
        jBtn1.setMinimumSize(new java.awt.Dimension(220, 23));
        jBtn1.setPreferredSize(new java.awt.Dimension(220, 23));
        jBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn1ActionPerformed(evt);
            }
        });

        jBtn2.setText("2");
        jBtn2.setFocusPainted(false);
        jBtn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtn2.setMaximumSize(new java.awt.Dimension(220, 23));
        jBtn2.setMinimumSize(new java.awt.Dimension(220, 23));
        jBtn2.setPreferredSize(new java.awt.Dimension(220, 23));
        jBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn2ActionPerformed(evt);
            }
        });

        jBtn3.setText("3");
        jBtn3.setFocusPainted(false);
        jBtn3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtn3.setMaximumSize(new java.awt.Dimension(220, 23));
        jBtn3.setMinimumSize(new java.awt.Dimension(220, 23));
        jBtn3.setPreferredSize(new java.awt.Dimension(220, 23));
        jBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn3ActionPerformed(evt);
            }
        });

        jBtn4.setText("4");
        jBtn4.setAlignmentX(272.0F);
        jBtn4.setAlignmentY(0.0F);
        jBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtn4.setFocusPainted(false);
        jBtn4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtn4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBtn4.setMaximumSize(new java.awt.Dimension(220, 23));
        jBtn4.setMinimumSize(new java.awt.Dimension(220, 23));
        jBtn4.setPreferredSize(new java.awt.Dimension(220, 23));
        jBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout msgPanelLayout = new javax.swing.GroupLayout(msgPanel);
        msgPanel.setLayout(msgPanelLayout);
        msgPanelLayout.setHorizontalGroup(
            msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(msgPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        msgPanelLayout.setVerticalGroup(
            msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msgPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jText, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(msgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(msgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(msgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //methods that react to button pushes and changes GameInformation
    private void jBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn1ActionPerformed
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        fg.changeInfo(s,1);
        if(s>=30||(s>19&&s<23)){ changeInfo(s+1);}
        else{
            setButtons(1);
        }
    }//GEN-LAST:event_jBtn1ActionPerformed

    private void jBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn3ActionPerformed
        this.setVisible(false);
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        fg.changeInfo(s,3);
    }//GEN-LAST:event_jBtn3ActionPerformed

    private void jBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn2ActionPerformed
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        fg.changeInfo(s,2);
        if(s>19&&s<23){changeInfo(s+1);}
        else{
            setButtons(2);
        }
    }//GEN-LAST:event_jBtn2ActionPerformed

    private void jBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn4ActionPerformed
        JuggleQuest fg = (JuggleQuest) SwingUtilities.getWindowAncestor(this);
        fg.changeInfo(s,4);
        if(s>19&&s<23){changeInfo(s+1);}
        else{
            setButtons(3);
        }
    }//GEN-LAST:event_jBtn4ActionPerformed

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
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Messages dialog = new Messages(new javax.swing.JFrame(), true);
                dialog.setUndecorated(true);
                dialog.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        //setDefaultCloseOperation(dialog.DO_NOTHING_ON_CLOSE);
                    }
                });
                dialog.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn1;
    private javax.swing.JButton jBtn2;
    private javax.swing.JButton jBtn3;
    private javax.swing.JButton jBtn4;
    private javax.swing.JLabel jPhoto;
    private javax.swing.JLabel jText;
    private javax.swing.JPanel msgPanel;
    // End of variables declaration//GEN-END:variables
}
