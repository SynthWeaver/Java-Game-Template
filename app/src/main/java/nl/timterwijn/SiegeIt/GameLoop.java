package nl.timterwijn.SiegeIt;


import android.os.Handler;
import android.util.Log;

public class GameLoop implements Runnable {

    public GameLoop(Handler MyHandler)
    {
        myHandler = MyHandler;
    }

    private boolean running = false;
    private Thread thread;
    private Handler myHandler;
    public boolean logicUpdate = false;


    public synchronized void start()
    {
        if (running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop()
    {
        if(!running)
        {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            if(logicUpdate == true)//alleen renderen als er een update is geweest;
            {
                logicUpdate = false;
                render();
                frames++;
            }
            else
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                Log.i("FPS", updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        int checkLogicUpdate = 0;

        if(checkLogicUpdate > 0)
        {
            logicUpdate = true;
        }
    }

    private void render()
    {
        myHandler.sendEmptyMessage(0);//render
    }
}
