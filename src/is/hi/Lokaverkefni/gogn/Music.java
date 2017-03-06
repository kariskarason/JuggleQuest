/*
 * Kári Snær Kárason - ksk12@hi.is
 * JuggleQuest lokaverkefni - 10/04/2016
 * 
 * Þessi klasi sér um spilun tónlistar. Hægt er að hefja/stoppa spilun, og breyta
 * um lög.
 */
package is.hi.Lokaverkefni.gogn;

import is.hi.Lokaverkefni.vidmot.JuggleQuest;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Kári
 */

public class Music {
    int start = 0;
    int song = 1;
    Clip clip;
    boolean on = true;
    //plays the music
    public void playMusic(){
        if(on&&song!=start){
        String lag="";
        if(!on) clip.stop();
        else{lag = "song" + song + ".wav";

        try {
            URL url = Music.class.getResource(lag);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(JuggleQuest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(JuggleQuest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JuggleQuest.class.getName()).log(Level.SEVERE, null, ex);
        }
        start = song;
        }
        }
    }
    //stops the music
    public void stopMusic(){
        on=!on;
        if(on) playMusic();
        else{
            clip.stop();
            start = 0;
        }  
    }
    public void changeMusic(int x){
        if(x!=song){
            song = x;
            if(on){
                clip.stop();
                playMusic();
            }
        }
    }
}
