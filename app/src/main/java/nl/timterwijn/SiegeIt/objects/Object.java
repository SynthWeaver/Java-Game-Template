package nl.timterwijn.SiegeIt.objects;

import android.widget.ImageView;

import java.util.Random;

public class Object{

    public Object()
    {
        Random ran = new Random();
        ID = ran.nextInt(1000000);
    }

    public int ID;
    public boolean visible = false;
    public float X;
    public float Y;

    public ImageView imageViewObject;

    public float velX = 0;
    public float velY = 0;
    public double angle;

    public boolean flying = false;
    protected float gravity = 0.03f;

    public int tick()
    {
        if (velX != 0 || velY != 0)
        {
            calcAngle();
            X += velX;
            Y += velY;
            calcGravity();
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private void calcAngle()
    {
        double x1 = X;
        double x2 = X + velX;
        double y1 = Y;
        double y2 = Y + velY;

        double radians = Math.atan2(y2-y1, x2-x1);

        angle = radians * ( 180 / Math.PI );
    }

    protected void calcGravity()
    {
        if (flying)
        {
            velY = velY + gravity;
            if (gravity >= 0.05f)
            {
                gravity = gravity + 0.001f;
            }
            else if (velY > 0)
            {
                gravity = 0.04f;
            }
        }
    }
}
