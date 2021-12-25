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

public class Main {
    
        public static void main(String[] args) {
            
        } try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
}

public class DummyGame implements IGameLogic {
    private int direction = 0;
    private float color = 0.0f;
    private final Renderer renderer;

    public DummyGame() {
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void input(Window window) {
        if ( window.isKeyPressed(GLFW_KEY_UP) ) {
            direction = 1;
        } else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(float interval) {
        color += direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if ( color < 0 ) {
                color = 0.0f;
        }
    }
    @Override
    public void render(Window window) {
        clear();
        if ( window.isResized() ) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        ShaderProgram.bind();

        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, 3);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}

public void start() {
    String osName = System.getProperty("os.name");
    if ( osName.contais("Mac") ) {
        gameLoopThread.run();
    } else {
        gameLoopThread.start();
    }
}