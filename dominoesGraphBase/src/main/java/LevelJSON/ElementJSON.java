package LevelJSON;

public class ElementJSON {

    private boolean top;
    private boolean bot;
    private boolean left;
    private boolean right;

    public ElementJSON(boolean top, boolean bot, boolean left, boolean right) {
        this.top = top;
        this.bot = bot;
        this.left = left;
        this.right = right;
    }

    public ElementJSON() {
        this(false, false, false, false);
    }

    public boolean isTop() {
        return top;
    }

    public boolean isBot() {
        return bot;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
