public class GameEngine implements Runnable {

    private final Thread gameLoopThread;

    public GameEngine(String windowTitle, int width, int height, boolean vsSync, IGameLogic gameLogic) throws Exception {
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        window = new Window(windowTitle, width, height, vsSync);
        this.gameLogic = gameLogic;
    }

    public void start() {
        gameLoopThread.start();
    }
    @Override
    public void run() {
    } try {
        init();
        gameLoop();
    } catch (Exception excp) {
        excp.printStackTrace();
    }
}

protected void input() {
    gameLogic.input(window);
}

protected void update(float interval) {
    gameLogic.update(interva);
}

protected void render() {
    gameLogic.render(window);
    window.update();
}
