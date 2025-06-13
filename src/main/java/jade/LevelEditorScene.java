package jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {
    private boolean changingScene = false;
    private float timeToChangeScreen = 2;

    public LevelEditorScene() {
        System.out.println("Inside level editor scene");
    }

    @Override
    public void update(float dt) {

        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }

        if (changingScene && timeToChangeScreen > 0) {
            timeToChangeScreen -= dt;
            Window.get().r -= dt * 5.f;
            Window.get().g -= dt * 5.f;
            Window.get().b -= dt * 5.f;
        } else if (changingScene) {
            Window.changeScene(1);
        }
    }

}
