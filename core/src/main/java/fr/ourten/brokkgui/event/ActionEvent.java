package fr.ourten.brokkgui.event;

import fr.ourten.brokkgui.control.GuiButtonBase;

public class ActionEvent extends GuiInputEvent
{
    public static final EventType<ActionEvent> TYPE = new EventType<>(GuiInputEvent.ANY, "ACTION_INPUT_EVENT");

    public ActionEvent(final GuiButtonBase source)
    {
        super(source);
    }
}