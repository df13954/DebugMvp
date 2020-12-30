package com.debug.base.utils;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * @author: 123
 * @date: 2020/11/27
 * @description $
 */
public class AppAnimatorUtils {
    public static ScaleAnimation scaleAnimation(float fromX,
                                                float fromY,
                                                float toX,
                                                float toY,
                                                long duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //动画执行时间
        scaleAnimation.setDuration(duration);
        //-1表示重复执行动画
        scaleAnimation.setRepeatCount(-1);
        //重复 缩小和放大效果
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        return scaleAnimation;
    }
}
