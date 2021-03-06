package fr.ourten.brokkgui.component;

import fr.ourten.brokkgui.data.ESide;
import fr.ourten.brokkgui.data.RelativeBindingHelper;
import fr.ourten.brokkgui.internal.IGuiRenderer;
import fr.ourten.brokkgui.paint.EGuiRenderPass;
import fr.ourten.brokkgui.panel.GuiTabPane;
import fr.ourten.teabeans.binding.BaseBinding;
import fr.ourten.teabeans.value.BaseProperty;

/**
 * @author Ourten 15 oct. 2016
 */
public class GuiTab
{
    private final BaseProperty<String>     textProperty;
    private final BaseProperty<GuiNode>    contentProperty;
    private final BaseProperty<GuiTabPane> tabPaneProperty;
    private final BaseProperty<Boolean>    selectedProperty;

    public GuiTab(final String text, final GuiNode content)
    {
        this.textProperty = new BaseProperty<>(text, "textProperty");
        this.contentProperty = new BaseProperty<>(content, "contentProperty");
        this.tabPaneProperty = new BaseProperty<>(null, "tabPaneProperty");
        this.selectedProperty = new BaseProperty<>(false, "selectedProperty");
    }

    public GuiTab(final String text)
    {
        this(text, null);
    }

    public GuiTab()
    {
        this("");
    }

    public BaseProperty<String> getTextProperty()
    {
        return this.textProperty;
    }

    public BaseProperty<GuiNode> getContentProperty()
    {
        return this.contentProperty;
    }

    public BaseProperty<GuiTabPane> getTabPaneProperty()
    {
        return this.tabPaneProperty;
    }

    public BaseProperty<Boolean> getSelectedProperty()
    {
        return this.selectedProperty;
    }

    public String getText()
    {
        return this.textProperty.getValue();
    }

    public void setText(final String text)
    {
        this.textProperty.setValue(text);
    }

    public GuiNode getContent()
    {
        return this.contentProperty.getValue();
    }

    public void setContent(final GuiNode content)
    {
        if (content != null && this.getTabPane() != null)
            this.setupContent(this.getTabPane(), content);
        if (this.getContent() != null)
            this.disposeContent();
        this.contentProperty.setValue(content);
    }

    public GuiTabPane getTabPane()
    {
        return this.tabPaneProperty.getValue();
    }

    public void setTabPane(final GuiTabPane tabPane)
    {
        if (tabPane != null && this.getContent() != null)
            this.setupContent(tabPane, this.getContent());
        else if (this.getContent() != null && tabPane == null)
            this.disposeContent();
        this.tabPaneProperty.setValue(tabPane);
    }

    private void setupContent(final GuiTabPane pane, final GuiNode content)
    {
        final BaseBinding<Float> xPadding = new BaseBinding<Float>()
        {
            {
                super.bind(pane.getSideProperty(), pane.getWidthProperty());
            }

            @Override
            public Float computeValue()
            {
                if (pane.getTabSide() == ESide.LEFT)
                    return (pane.getWidth() / 10) + 1;
                return 1f;
            }
        };
        final BaseBinding<Float> yPadding = new BaseBinding<Float>()
        {
            {
                super.bind(pane.getSideProperty(), pane.getHeightProperty());
            }

            @Override
            public Float computeValue()
            {
                if (pane.getTabSide() == ESide.UP)
                    return (pane.getHeight() / 10) + 1;
                return 1f;
            }
        };

        this.getContent().setFather(pane);
        RelativeBindingHelper.bindToPos(content, pane, xPadding, yPadding);
    }

    private void disposeContent()
    {
        this.getContent().getxPosProperty().unbind();
        this.getContent().getyPosProperty().unbind();
        this.getContent().setFather(null);
    }

    public boolean isSelected()
    {
        return this.selectedProperty.getValue();
    }

    public void setSelected(final boolean selected)
    {
        this.selectedProperty.setValue(selected);
    }

    public void renderNode(final IGuiRenderer renderer, final EGuiRenderPass pass, final int mouseX, final int mouseY)
    {
        if (this.getContent() != null)
            this.getContent().renderNode(renderer, pass, mouseX, mouseY);
    }
}