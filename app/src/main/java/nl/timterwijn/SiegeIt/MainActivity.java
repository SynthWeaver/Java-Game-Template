package nl.timterwijn.SiegeIt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity{

    //Propeties////////////////////////////////////////////////////////////////////////////////////////////////
    private Handler myHandler = new Handler() {
        public void handleMessage(Message message) {
            switch(message.what) {
                case 0://render
                    render(); break;
            }
        }
    };

    private RelativeLayout app;//de app

    //Overrides///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (RelativeLayout)findViewById(R.id.activity_main);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            System.out.println("onWindowFocusChanged");
            renderApp();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            float x = event.getX();
            float y = event.getY();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            float x = event.getX();
            float y = event.getY();
        }
        return false;
    }

    //Methods/////////////////////////////////////////////////////////////////////////////////////////////////

    private void renderApp()
    {
        //threads.GO
        new GameLoop(myHandler).start();
    }

    private void render()
    {

    }
}
