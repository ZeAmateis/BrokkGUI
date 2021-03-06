package fr.ourten.brokkgui.element;

import fr.ourten.brokkgui.behavior.GuiButtonBehavior;
import fr.ourten.brokkgui.control.GuiButtonBase;
import fr.ourten.brokkgui.data.EAlignment;
import fr.ourten.brokkgui.skin.GuiButtonSkin;
import fr.ourten.brokkgui.skin.GuiSkinBase;

public class GuiButton extends GuiButtonBase
{
    public GuiButton(final String text)
    {
        super(text);
        this.setTextAlignment(EAlignment.MIDDLE_CENTER);
    }

    public GuiButton()
    {
        this("");
    }

    @Override
    protected GuiSkinBase<?> makeDefaultSkin()
    {
        return new GuiButtonSkin(this, new GuiButtonBehavior<>(this));
    }
}