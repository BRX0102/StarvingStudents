package edu.csumb.hashmapsallday.hungrylittlemonsters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Created by brand on 11/4/2016.
 */

public class FeedMe extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {
    private AnimatorSet set;
    private Animation zoomAnimation;
    private ImageView imgView,zoom,cardImage;
    private Button image1,image2,image3,feedMe;
    private int experiencePoints=100;
    private FrameLayout frame;
    private float x1,x2,y1,y2,dx,dy;
    private int slide=0;
    private Toast toast;
    private Context thisContext;
    private Handler mainHandler;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_me);


         image1 =(Button) findViewById(R.id.image1);
         image2 =(Button) findViewById(R.id.image2);
         image3 =(Button) findViewById(R.id.image3);

        frame = (FrameLayout)findViewById(R.id.container);
        image1.setOnTouchListener(this);
        image2.setOnTouchListener(this);
        image3.setOnTouchListener(this);
        feedMe = (Button)findViewById(R.id.feedMeButton);
        feedMe.setOnClickListener(this);
        expBar();
        //ImageView monsterImage = (ImageView) findViewById(R.id.monsterImage);
        expression();

    }


    public void expBar()
    {
         thisContext = getApplicationContext();
          mainHandler = new Handler(thisContext.getMainLooper());
        final ProgressBar experience = (ProgressBar)findViewById(R.id.expBar);
        final Thread t = new Thread() {
            @Override
            public void run() {

                double jumpTime = 100;

                while(jumpTime > 2) {
                    try {
                        sleep(200);
                        jumpTime -= 2;
                        experiencePoints = (int)jumpTime;
                       experience.setProgress(experiencePoints);


                               final ImageView monsterImg = (ImageView) findViewById(R.id.monsterImage);
                                if (experiencePoints < 50)

                                {
                                  mainHandler.post(new Runnable() {
                                      @Override
                                      public void run() {
                                          monsterImg.setImageResource(R.mipmap.other);
                                      }
                                  });


                                }


                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

    }
    public void expression()
    {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.feedMeButton:
                frame.setVisibility(View.VISIBLE);

                break;

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action =motionEvent.getAction();


        switch(view.getId()) {
            case R.id.image1:

                if(action==MotionEvent.ACTION_DOWN)
                {
                    x1 = motionEvent.getX();
                    y1 = motionEvent.getY();

                }else if(action==MotionEvent.ACTION_UP)
                {
                    x2 = motionEvent.getX();
                    y2 = motionEvent.getY();
                    dx = x2-x1;
                    dy = y2-y1;

                    // Use dx and dy to determine the direction
                    if(Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) {
                            //right slide
                        } else {
                            //left slide
                        }
                    }
                    else {
                        if(dy>0) {
                            //down slide
                        }
                        else {
                            //up slide


                            ////////////////////////////////////////
                            thisContext = getApplicationContext();
                            mainHandler = new Handler(thisContext.getMainLooper());
                            final Thread t = new Thread() {
                                @Override
                                public void run() {


                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.flipping);
                                            set.setTarget(image1);
                                            set.start();
                                            Intent myIntent = new Intent(getApplicationContext(), CardBack.class);
                                            startActivity(myIntent);
                                        }
                                    });

                                }

                            };
                            t.start();
                            /////////////////////////////////////////////////////////
                        }
                    }
                    view.setElevation(2);
                    image2.setElevation(1);
                    image3.setElevation(0);
                }
            break;
            case R.id.image2:

                if(action==MotionEvent.ACTION_DOWN)
                {
                    x1 = motionEvent.getX();
                    y1 = motionEvent.getY();

                }else if(action==MotionEvent.ACTION_UP)
                {
                    x2 = motionEvent.getX();
                    y2 = motionEvent.getY();
                    dx = x2-x1;
                    dy = y2-y1;

                    // Use dx and dy to determine the direction
                    if(Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) {
                            //right slide

                        } else {
                            //left slide

                        }
                    }
                    else {
                        if(dy>0) {
                            //down slide
                        }
                        else {
                            //up slide
                            ////////////////////////////////////////
                            thisContext = getApplicationContext();
                            mainHandler = new Handler(thisContext.getMainLooper());
                            final Thread t = new Thread() {
                                @Override
                                public void run() {


                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.flipping);
                                            set.setTarget(image2);
                                            set.start();
                                            Intent myIntent = new Intent(getApplicationContext(), CardBack.class);
                                            startActivity(myIntent);
                                        }
                                    });

                                }

                            };
                            t.start();
                            /////////////////////////////////////////////////////////
                        }
                    }
                    view.setElevation(2);
                    image1.setElevation(0);
                    image3.setElevation(0);
                }
                break;
            case R.id.image3:
                if(action==MotionEvent.ACTION_DOWN)
                {
                    x1 = motionEvent.getX();
                    y1 = motionEvent.getY();

                }else if(action==MotionEvent.ACTION_UP)
                {
                    x2 = motionEvent.getX();
                    y2 = motionEvent.getY();
                    dx = x2-x1;
                    dy = y2-y1;

                    // Use dx and dy to determine the direction
                    if(Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) {
                          //right slide
                        } else {
                            //left slide
                        }
                    }
                    else {
                        if(dy>0) {
                        //down slide
                        }
                        else {
                           //up slide

                            ////////////////////////////////////////
                            thisContext = getApplicationContext();
                            mainHandler = new Handler(thisContext.getMainLooper());
                            final Thread t = new Thread() {
                                @Override
                                public void run() {


                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.flipping);
                                            set.setTarget(image3);
                                            set.start();
                                            Intent myIntent = new Intent(getApplicationContext(), CardBack.class);
                                            startActivity(myIntent);
                                        }
                                    });

                                }

                            };
                            t.start();
                            /////////////////////////////////////////////////////////
                        }
                    }
                    slide=0;
                    view.setElevation(2);
                    image1.setElevation(0);
                    image2.setElevation(0);
                }
                break;
        }
        return false;
    }


}
