package com.nafuto.countdownview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class CountDownView extends TextView{

    private Animation mAnimation;
    private int mStartCount;
    private int mCurrentCount;
    private CountDownListener mListener;

    private Handler mHandler = new Handler();

    private final Runnable mCountDown = new Runnable() {
        public void run() {
            if (mCurrentCount > 0) {
                CountDownView.this.setText(mCurrentCount + "");
                CountDownView.this.startAnimation(mAnimation);
                mCurrentCount--;
            } else {
                CountDownView.this.setVisibility(View.GONE);
                if (mListener != null)
                    mListener.onCountDownEnd(CountDownView.this);
            }
        }
    };

    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * <p>
     * Creates a count down animation in the <var>textView</var>, starting from
     * <var>startCount</var>.
     * </p>
     * <p>
     * By default, the class defines a fade out animation, which uses
     * {@link AlphaAnimation } from 1 to 0.
     * </p>
     *
     * @param startCount
     *            The starting count number
     */
    public void initCountDownView(int startCount) {
        this.mStartCount = startCount;
        CountDownView.this.setBackgroundResource(R.drawable.count_down_view);

//        mAnimation = new AlphaAnimation(1.0f, 0.0f);
//        mAnimation.setDuration(1000);
//
//        mAnimation = new RotateAnimation(0,360);
//        mAnimation.setDuration(1000);

        mAnimation = new ScaleAnimation(1f,1.5f,1f,1.5f,1, 0.5f, 1, 0.5f);
        mAnimation.setDuration(300);
        mAnimation = new ScaleAnimation(1.5f,1f,1.5f,1f,1, 0.5f, 1, 0.5f);
        mAnimation.setDuration(300);

//        mAnimation = new TranslateAnimation(0,100,0,100);
//        mAnimation.setDuration(1000);
    }

    /**
     * Starts the count down animation.
     */
    public void start() {
        mHandler.removeCallbacks(mCountDown);

        CountDownView.this.setText(mStartCount + "");
        CountDownView.this.setVisibility(View.VISIBLE);

        mCurrentCount = mStartCount;

        mHandler.post(mCountDown);
        for (int i = 1; i <= mStartCount; i++) {
            mHandler.postDelayed(mCountDown, i * 1000);
        }
    }

    /**
     * Cancels the count down animation.
     */
    public void cancel() {
        mHandler.removeCallbacks(mCountDown);

        CountDownView.this.setText("");
        CountDownView.this.setVisibility(View.GONE);
    }

    /**
     * Sets the animation used during the count down. If the duration of the
     * animation for each number is not set, one second will be defined.
     */
    public void setAnimation(Animation animation) {
        this.mAnimation = animation;
        if (mAnimation.getDuration() == 0)
            mAnimation.setDuration(1000);
    }

    /**
     * Returns the animation used during the count down.
     */
    public Animation getAnimation() {
        return mAnimation;
    }

    /**
     * Sets a new starting count number for the count down animation.
     *
     * @param startCount
     *            The starting count number
     */
    public void setStartCount(int startCount) {
        this.mStartCount = startCount;
    }

    /**
     * Returns the starting count number for the count down animation.
     */
    public int getStartCount() {
        return mStartCount;
    }

    /**
     * Binds a listener to this count down animation. The count down listener is
     * notified of events such as the end of the animation.
     *
     * @param listener
     *            The count down listener to be notified
     */
    public void setCountDownListener(CountDownListener listener) {
        mListener = listener;
    }

    /**
     * A count down listener receives notifications from a count down animation.
     * Notifications indicate count down animation related events, such as the
     * end of the animation.
     */
    public interface CountDownListener {
        /**
         * Notifies the end of the count down animation.
         *
         * @param animation
         *            The count down animation which reached its end.
         */
        void onCountDownEnd(CountDownView animation);
    }
}
