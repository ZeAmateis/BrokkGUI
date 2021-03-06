package fr.ourten.brokkgui.event;

import fr.ourten.brokkgui.component.GuiNode;

public class CursorMoveEvent extends GuiMouseEvent
{
    public static final EventType<CursorMoveEvent> TYPE = new EventType<>(GuiMouseEvent.ANY, "MOUSE_MOVE_EVENT");

    public CursorMoveEvent(final GuiNode source)
    {
        this(source, 0, 0);
    }

    public CursorMoveEvent(final GuiNode source, final int mouseX, final int mouseY)
    {
        super(source, mouseX, mouseY);
    }
}