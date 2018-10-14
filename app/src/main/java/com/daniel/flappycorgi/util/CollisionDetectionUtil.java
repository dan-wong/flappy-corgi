package com.daniel.flappycorgi.util;

import com.daniel.flappycorgi.sprites.Sprite;

public class CollisionDetectionUtil {
    public static boolean detectCollision(Sprite spriteOne, Sprite spriteTwo) {
        return spriteOne.getSpriteData().x < spriteTwo.getSpriteData().x + spriteTwo.getWidth() &&
                spriteOne.getSpriteData().x + spriteOne.getWidth() > spriteTwo.getSpriteData().x &&
                spriteOne.getSpriteData().y < spriteTwo.getSpriteData().y + spriteTwo.getHeight() &&
                spriteOne.getSpriteData().y + spriteOne.getHeight() > spriteTwo.getSpriteData().y;
    }
}
