double secsPerUpdate = 1.0d / 30.0d;
double previous = getTime();
double steps = 0.0;
while (true) {
    double loopStartTime = getTime();
    double elapsed = loopStartTime - previous;
    previous = current;
    steps += elapsed;

    handleInput();

    while (steps >= secsPerUpdate) {
        updateGameState();
        steps -= secsPerUpdate;
    }

    render();
    sync(current);
}

private void sync(double loopStartTime) {
    float loopSlot = 1f / 50;
    double endTime = loopStartTime + loopSlot;
    while(getTime() < endTime) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {}
    }
}

//setup resize callback
glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
    Window.this.width = width;
    Window.this.height = height;
    Window.this.setResized(true);
});

public void init() throws Exception {

}
public void clear() {
    glClear(GL_COLOR_BUFFET_BIT | GL_DEPTH_BUFFER_BIT);
}

public interface IGameLogic {
    void init() throws Exception;
    void input(Window window);
    void update(float interval);
    void render(Window window);
}

