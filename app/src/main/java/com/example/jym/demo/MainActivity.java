package com.example.jym.demo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.dd.CircularProgressButton;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    ValueAnimator widthAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getState() == CircularProgressButton.State.MORPHING) {
                    return;
                }
                if (circularButton1.getProgress() == 0 && circularButton1.getState() == CircularProgressButton.State.IDLE) {
                    simulateSuccessProgress(circularButton1);
                } else {
                    widthAnimation.cancel();
                    circularButton1.setProgress(0);
                    circularButton1.setText("count\n5s");
                    simulateSuccessProgress(circularButton1);
                }
            }
        });

    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(5000);
        widthAnimation.setInterpolator(new LinearInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if ((100 - value) % 20 == 0) {
                    button.setText("count\n" + (100 - value) / 100f * 5 + "s");
                }
            }
        });
        widthAnimation.start();
    }
}
