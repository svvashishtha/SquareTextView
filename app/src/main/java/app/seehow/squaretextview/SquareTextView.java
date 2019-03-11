package app.seehow.squaretextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class SquareTextView extends AppCompatTextView {
    private static final float DEFAULT_RADIUS_VALUE = 8;
    RectF rect;
    Paint paint;
    int backgroundColor;
    float radius;
    int max = -1;

    public SquareTextView(Context context) {
        super(context);
        init(null);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        rect = new RectF();
        rect.left = rect.top = 0;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        setTextAlignment(TEXT_ALIGNMENT_CENTER);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SquareTextView);
        backgroundColor = ta.getColor(R.styleable.SquareTextView_backgroundColor, Color.TRANSPARENT);
        radius = ta.getDimension(R.styleable.SquareTextView_cornerRadius, DEFAULT_RADIUS_VALUE);
        paint.setColor(backgroundColor);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (max == -1) {
            max = Math.max(getWidth(), getHeight());
            setWidth(max);
            setHeight(max);

            rect.bottom = max;
            rect.right = max;
            canvas.drawRoundRect(rect, radius, radius, paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        invalidate();
        requestLayout();
    }
}
