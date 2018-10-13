package com.daniel.flappycorgi.util;

import com.daniel.flappycorgi.dataclass.SpriteData;

public class PhysicsEngineUtil {
    public static final double GRAVITY = 2.5;

    public static void calculateNewPosition(SpriteData spriteData, double yAcceleration) {
        if (yAcceleration < 0 && spriteData.yVelocity > 0) {
            spriteData.yVelocity = 0;
        }

        spriteData.yVelocity += yAcceleration;
        spriteData.y += Math.ceil(spriteData.yVelocity);
    }
}
