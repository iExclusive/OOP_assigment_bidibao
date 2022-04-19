package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio3 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float offset;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("darude.wav", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
			case 0:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                }
                break;
        case 1:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                //float c = map(ab.get(i), -1, 1, 0, 255);
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                line(i, halfH + f, halfH - f, i);                    
            }
            break;
        case 2:
            break;
        case 3:
            background(0);
            break;

        case 4:
        
            background(0);
                  
            break;
            case 5:
            background(0);
            strokeWeight(2);
            for(int i = 0 ; i < ab.size() ; i +=10)
            {
                //float c = map(ab.get(i), -1, 1, 0, 255);
                float cc = map(i, 0, ab.size(), 0, 255);
                stroke(cc, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                circle(i, halfH + f,  halfH - f); // put i 3rd parameter
                fill(cc);
                circle(i, halfH + f, 100);                    
                circle(i, halfH - f, 100);                   
            }
            break;
            case 6:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                float g = map(ab.get(i), -1, 1, 0, 255);
                //float g = map(i, 0, ab.size(), 0, 255);
                stroke(g, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                triangle(i, halfH + f, i, halfH - f, i, i);  
                circle(i, halfH + f,  halfH - f);                  
            }
            break;
            case 7:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                //background(0);
                float g = map(ab.get(i), -1, 1, 0, 255);
                //float g = map(i, 0, ab.size(), 0, 255);
                stroke(g, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                //line(halfH + f, halfH - f, i++, i++);
                  
                circle(halfH--, halfH++, f);               
            }
            break;
            case 8:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {

                float u = map(ab.get(i), -1, 1, 0, 255);
                stroke(u, 255, 255);
                float f = lerpedBuffer[i] * halfH / 1.0f;
                line(halfH + f, halfH - f, i++, i++);
            }
            break;


            case 9:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {

                float u = map(ab.get(i), -1, 1, 0, 100);
                stroke(u, 100, 100);
                float f = lerpedBuffer[i] * halfH * 12.0f;
                line(halfH + f, halfH - f, i++, i++);
            }
            break;


        }
        

    }        
}
