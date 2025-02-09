package codechicken.nei;

import static codechicken.lib.gui.GuiDraw.getStringWidth;

import java.util.List;

public abstract class Button extends Widget {
    public Button(String s) {
        label = s;
    }

    public Button() {
        label = "";
    }

    public int contentWidth() {
        return getRenderIcon() == null ? getStringWidth(label) : getRenderIcon().width;
    }

    @Override
    public void draw(int mousex, int mousey) {
        LayoutManager.getLayoutStyle().drawButton(this, mousex, mousey);
    }

    @Override
    public boolean handleClick(int mx, int my, int button) {
        if (button == 1 || button == 0) if (onButtonPress(button == 1)) NEIClientUtils.playClickSound();
        return true;
    }

    public abstract boolean onButtonPress(boolean rightclick);

    public Image getRenderIcon() {
        return icon;
    }

    @Override
    public List<String> handleTooltip(int mx, int my, List<String> tooltip) {
        if (!contains(mx, my)) return tooltip;

        final String tip = getButtonTip();
        if (tip != null) tooltip.add(tip);

        addTooltips(tooltip);

        return tooltip;
    }

    public String getButtonTip() {
        return null;
    }

    public void addTooltips(List<String> tooltip) {}

    public String getRenderLabel() {
        return label;
    }

    public final String label;
    public Image icon;

    /**
     * 0x4 = state flag, as opposed to 1 click
     * 0 = normal
     * 1 = on
     * 2 = disabled
     */
    public int state;
}
