package jade;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {
    // essentially a data structure that holds current mouse state for the engine, abstracting out the logic for keeping the data up to date.
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3]; // 0 - left click, 1 - right click, 2 - middle button
    private boolean isDragging;

    private MouseListener() {
        // constructs a new object to store mouse data
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get() {
        // returns the current mouse listener or constructs a new one if there are no instances
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        // updates the listener with new position data, sets the current x,y positions to last then sets the new x, y positions
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;

        // we can tell the mouse is being dragged if there is a button being pressed when this callback is called
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        // updates the listener with the current buttons being pressed
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) { // check for a mouse with more than 3 buttons
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float)get().xPos;
    }

    public static float getY() {
        return (float)get().yPos;
    }

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else return false;
    }


}
