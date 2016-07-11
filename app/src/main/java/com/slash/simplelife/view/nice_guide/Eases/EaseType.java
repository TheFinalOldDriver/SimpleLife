package com.slash.simplelife.view.nice_guide.Eases;

import com.nightonke.wowoviewpager.Eases.*;
import com.nightonke.wowoviewpager.Eases.CubicBezier;
import com.nightonke.wowoviewpager.Eases.EaseInBack;
import com.nightonke.wowoviewpager.Eases.EaseInBounce;
import com.nightonke.wowoviewpager.Eases.EaseInCirc;
import com.nightonke.wowoviewpager.Eases.EaseInCubic;
import com.nightonke.wowoviewpager.Eases.EaseInElastic;
import com.nightonke.wowoviewpager.Eases.EaseInExpo;
import com.nightonke.wowoviewpager.Eases.EaseInOutBack;
import com.nightonke.wowoviewpager.Eases.EaseInOutBounce;
import com.nightonke.wowoviewpager.Eases.EaseInOutCirc;
import com.nightonke.wowoviewpager.Eases.EaseInOutCubic;
import com.nightonke.wowoviewpager.Eases.EaseInOutElastic;
import com.nightonke.wowoviewpager.Eases.EaseInOutExpo;
import com.nightonke.wowoviewpager.Eases.EaseInOutQuad;
import com.nightonke.wowoviewpager.Eases.EaseInOutQuart;
import com.nightonke.wowoviewpager.Eases.EaseInOutQuint;
import com.nightonke.wowoviewpager.Eases.EaseInOutSine;
import com.nightonke.wowoviewpager.Eases.EaseInQuad;
import com.nightonke.wowoviewpager.Eases.EaseInQuart;
import com.nightonke.wowoviewpager.Eases.EaseInQuint;
import com.nightonke.wowoviewpager.Eases.EaseOutBack;
import com.nightonke.wowoviewpager.Eases.EaseOutBounce;
import com.nightonke.wowoviewpager.Eases.EaseOutCirc;
import com.nightonke.wowoviewpager.Eases.EaseOutCubic;
import com.nightonke.wowoviewpager.Eases.EaseOutElastic;
import com.nightonke.wowoviewpager.Eases.EaseOutExpo;
import com.nightonke.wowoviewpager.Eases.EaseOutQuad;
import com.nightonke.wowoviewpager.Eases.EaseOutQuart;
import com.nightonke.wowoviewpager.Eases.EaseOutQuint;
import com.nightonke.wowoviewpager.Eases.EaseOutSine;

/**
 * Created by Weiping on 2016/3/3.
 */

public enum EaseType {
    
    EaseInSine(com.nightonke.wowoviewpager.Eases.EaseInSine.class),
    EaseOutSine(com.nightonke.wowoviewpager.Eases.EaseOutSine.class),
    EaseInOutSine(com.nightonke.wowoviewpager.Eases.EaseInOutSine.class),

    EaseInQuad(com.nightonke.wowoviewpager.Eases.EaseInQuad.class),
    EaseOutQuad(com.nightonke.wowoviewpager.Eases.EaseOutQuad.class),
    EaseInOutQuad(com.nightonke.wowoviewpager.Eases.EaseInOutQuad.class),

    EaseInCubic(com.nightonke.wowoviewpager.Eases.EaseInCubic.class),
    EaseOutCubic(com.nightonke.wowoviewpager.Eases.EaseOutCubic.class),
    EaseInOutCubic(com.nightonke.wowoviewpager.Eases.EaseInOutCubic.class),

    EaseInQuart(com.nightonke.wowoviewpager.Eases.EaseInQuart.class),
    EaseOutQuart(com.nightonke.wowoviewpager.Eases.EaseOutQuart.class),
    EaseInOutQuart(com.nightonke.wowoviewpager.Eases.EaseInOutQuart.class),

    EaseInQuint(com.nightonke.wowoviewpager.Eases.EaseInQuint.class),
    EaseOutQuint(com.nightonke.wowoviewpager.Eases.EaseOutQuint.class),
    EaseInOutQuint(com.nightonke.wowoviewpager.Eases.EaseInOutQuint.class),

    EaseInExpo(com.nightonke.wowoviewpager.Eases.EaseInExpo.class),
    EaseOutExpo(com.nightonke.wowoviewpager.Eases.EaseOutExpo.class),
    EaseInOutExpo(com.nightonke.wowoviewpager.Eases.EaseInOutExpo.class),

    EaseInCirc(com.nightonke.wowoviewpager.Eases.EaseInCirc.class),
    EaseOutCirc(com.nightonke.wowoviewpager.Eases.EaseOutCirc.class),
    EaseInOutCirc(com.nightonke.wowoviewpager.Eases.EaseInOutCirc.class),

    EaseInBack(com.nightonke.wowoviewpager.Eases.EaseInBack.class),
    EaseOutBack(com.nightonke.wowoviewpager.Eases.EaseOutBack.class),
    EaseInOutBack(com.nightonke.wowoviewpager.Eases.EaseInOutBack.class),

    EaseInElastic(com.nightonke.wowoviewpager.Eases.EaseInElastic.class),
    EaseOutElastic(com.nightonke.wowoviewpager.Eases.EaseOutElastic.class),
    EaseInOutElastic(com.nightonke.wowoviewpager.Eases.EaseInOutElastic.class),

    EaseInBounce(com.nightonke.wowoviewpager.Eases.EaseInBounce.class),
    EaseOutBounce(com.nightonke.wowoviewpager.Eases.EaseOutBounce.class),
    EaseInOutBounce(com.nightonke.wowoviewpager.Eases.EaseInOutBounce.class),

    Linear(Linear.class);

    private Class easingType;

    /**
     * ease animation helps to make the movement more real
     * @param easingType
     */
    EaseType(Class easingType) {
        this.easingType = easingType;
    }

    public float getOffset(float offset) {
        try {
            return ((CubicBezier) easingType.getConstructor().newInstance()).getOffset(offset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("CubicBezier init error.");
        }
    }

}
