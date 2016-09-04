package timkranen.com.wtfay.views.main_page;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.github.florent37.viewanimator.ViewAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import timkranen.com.wtfay.R;
import timkranen.com.wtfay.utils.DensityUtil;

/**
 * @author tim on [8/16/16]
 */
public class SearchLocationView extends FrameLayout {
    private static final String TAG = SearchLocationView.class.getSimpleName();

    private int height;
    private boolean isMeasured;
    private int targetHeight;
    private int targetWidth;
    private float currentY;
    private int animationLatchMargin = 50; //the margin the view uses to animate before reaching the bottom

    private boolean hasLockedBottom = false; //set to true if the locationsearchview has latched itself on the bottom of the screen

    //views
    @BindView(R.id.location_text_bounding_box)
    FrameLayout boundingBox;
    @BindView(R.id.overlay)
    View overlayView;

    public SearchLocationView(Context context) {
        super(context);

        initialize();
    }

    public SearchLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public SearchLocationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.search_location_view, this);

        ButterKnife.bind(this);

        overlayView.setAlpha(.5f);

        startMeasurements();
    }

    /**
     * Measures the view own height for later use
     */
    private void startMeasurements() {
        boundingBox.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (getHeight() > 0) {
                    isMeasured = true;

                    height = boundingBox.getHeight();
                    currentY = boundingBox.getY();

                    boundingBox.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /**
     * Sets the view this view
     * needs to latch on to. This view will
     * latch onto the bottom of the targetview
     */
    public void setLatchView(final View targetView) {
        targetView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (targetView.getHeight() > 0) {
                    targetView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    targetHeight = targetView.getHeight();
                    targetWidth = targetView.getWidth();
                }
            }
        });
    }

    public void scrollBottom(int dy) {
        if (isMeasured && !hasLockedBottom) {
            if (hasReachedBottom(dy)) {
                latch();
            }

            currentY += dy;
            boundingBox.setY(currentY);
            setOverlayAlpha(dy);

            //calculate % off screen scroll
            float factorScreenScroll = currentY / targetHeight;
            startLatchAnimation(targetWidth * factorScreenScroll, 10);
        }
    }

    private void setOverlayAlpha(int dy) {
        float y = currentY + dy;
        float dalpha = (y * 100) / targetHeight;
        overlayView.setAlpha((100 - dalpha) / 100);
    }

    private void latch() {
        hasLockedBottom = true;
        currentY = getBottomLatchPoint();
        boundingBox.setY(currentY);

        startLatchAnimation(targetWidth, 10);
    }

    private void startLatchAnimation(float targetWidth, int duration) {
        LayoutParams layoutParams = (LayoutParams) boundingBox.getLayoutParams();
        layoutParams.width = (int) targetWidth;
        boundingBox.setLayoutParams(layoutParams);


//        ViewAnimator.animate(boundingBox)
//                .width(boundingBox.getWidth(), targetWidth)
//                .interpolator(new AccelerateDecelerateInterpolator())
//                .duration(duration)
//                .start();
    }

    private int getBottomLatchPoint() {
        if (isMeasured) {
            return targetHeight - height;
        } else {
            return 0;
        }
    }

    private boolean hasReachedBottom(int dy) {
        return currentY + dy > (getBottomLatchPoint());
    }
}
