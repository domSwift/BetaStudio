package com.example.betastudio.general;

import android.graphics.Color;

public class ColorBasedPriority {

    static int get(Double n) {

        int r = (int) Math.round(n) * 20;
        int b = 0;
        int g = 245;

        if (r < 245) {
            return Color.argb(255, r, g, b);
        }

        else{
            g = 245 - (r - 245);
            if(g<0){
                return Color.argb(255, 245, 0, b);
            }
            else{
                return Color.argb(255, 245, g, b);
            }
        }

    }

}

//END... :)