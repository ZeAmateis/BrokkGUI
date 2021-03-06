package fr.ourten.brokkgui.shape;

import fr.ourten.brokkgui.internal.IGuiRenderer;
import fr.ourten.brokkgui.paint.EGuiRenderPass;

public class Rectangle extends GuiShape
{
    public Rectangle(final float xLeft, final float yLeft, final float width, final float height)
    {
        this.setxTranslate(xLeft);
        this.setyTranslate(yLeft);

        this.setWidth(width);
        this.setHeight(height);
    }

    public Rectangle(final float width, final float height)
    {
        this(0, 0, width, height);
    }

    public Rectangle()
    {
        this(0, 0, 0, 0);
    }

    @Override
    public void renderNode(final IGuiRenderer renderer, final EGuiRenderPass pass, final int mouseX, final int mouseY)
    {
        if (pass == EGuiRenderPass.MAIN)
        {
            if (this.getLineWeight() > 0)
                renderer.getHelper().drawColoredEmptyRect(renderer, this.getxPos() + this.getxTranslate(),
                        this.getyPos() + this.getyTranslate(), this.getWidth(), this.getHeight(), this.getzLevel(),
                        this.getLineColor(), this.getLineWeight());
            renderer.getHelper().drawColoredRect(renderer, this.getxPos() + this.getxTranslate(),
                    this.getyPos() + this.getyTranslate(), this.getWidth(), this.getHeight(), this.getzLevel(),
                    this.getColor());
        }
    }
}