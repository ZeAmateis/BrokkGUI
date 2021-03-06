package fr.ourten.brokkgui.skin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.ourten.brokkgui.behavior.GuiListViewBehavior;
import fr.ourten.brokkgui.element.GuiListCell;
import fr.ourten.brokkgui.element.GuiListView;
import fr.ourten.brokkgui.event.ClickEvent;
import fr.ourten.brokkgui.internal.IGuiRenderer;
import fr.ourten.brokkgui.paint.EGuiRenderPass;
import fr.ourten.teabeans.listener.ListValueChangeListener;

public class GuiListViewSkin<T> extends GuiBehaviorSkinBase<GuiListView<T>, GuiListViewBehavior<T>>
{
    private List<GuiListCell<T>> listCell;

    public GuiListViewSkin(final GuiListView<T> model, final GuiListViewBehavior<T> behaviour)
    {
        super(model, behaviour);

        this.reloadAllCells();

        this.getModel().getElementsProperty()
                .addListener((ListValueChangeListener<T>) (observable, oldValue, newValue) -> this.reloadAllCells());

        this.getModel().getEventDispatcher().addHandler(ClickEvent.TYPE, this::onClick);
    }

    /**
     * Used to transfer ClickEvent to the right node inside the ListView. If the
     * list does not contains any GuiListCell it's forwarded to the placeholder
     * node.
     *
     * @param e
     */
    public void onClick(final ClickEvent e)
    {
        if (!this.listCell.isEmpty())
        {
            final Optional<GuiListCell<T>> rtn = this.listCell.stream()
                    .filter(cell -> cell.isPointInside(e.getMouseX(), e.getMouseY())).findFirst();

            if (rtn.isPresent())
            {
                this.getBehavior().selectCell(this.listCell.indexOf(rtn.get()));
                rtn.get().handleClick(e.getMouseX(), e.getMouseY(), e.getKey());
            }
        }
        else if (this.getModel().getPlaceholder() != null
                && this.getModel().getPlaceholder().isPointInside(e.getMouseX(), e.getMouseY()))
            this.getModel().getPlaceholder().handleClick(e.getMouseX(), e.getMouseY(), e.getKey());
    }

    @Override
    public void render(final EGuiRenderPass pass, final IGuiRenderer renderer, final int mouseX, final int mouseY)
    {
        super.render(pass, renderer, mouseX, mouseY);

        if (!this.listCell.isEmpty())
            this.listCell.forEach(cell -> cell.renderNode(renderer, pass, mouseX, mouseY));
        else if (this.getModel().getPlaceholder() != null)
            this.getModel().getPlaceholder().renderNode(renderer, pass, mouseX, mouseY);
    }

    public void reloadAllCells()
    {
        this.listCell = this.getModel().getElements().stream().map(this.getModel().getCellFactory()::apply)
                .collect(Collectors.toList());
    }
}