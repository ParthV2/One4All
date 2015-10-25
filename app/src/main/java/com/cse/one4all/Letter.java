package com.cse.one4all;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by matthew on 10/25/2015.
 */
public class Letter extends Text
{
    private float positionY;
    private float positionX;

    public Letter(float pX, float pY, IFont pFont, CharSequence pText, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pFont, pText, pVertexBufferObjectManager);
        setPy(pY);
        setPx(pX);
    }
    public void setPosition(float pX, float pY)
    {
        positionX = pX;
        positionY = pY;
        super.setPosition(positionX, positionY);
    }
    public void setPy(float pY)
    {
        positionY = pY;
    }
    public float getPy()
    {
        return positionY;
    }
    public void setPx(float pX)
    {
        positionX = pX;
    }
    public float getPx()
    {
        return positionX;
    }
}
