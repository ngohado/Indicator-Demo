package com.hado.progressbarlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Hado on 5/11/17.
 */

public class ProgressBar extends View {

    private PointF pointCenter;

    private float circleRadius;

    private float strokeWidth = 12; // stroke width of circle progress

    private float currentAngle = 0;

    private int currentProgress = 0;

    private int dashLength = 8; // độ dài mấy cái gạch gạch xung quan đó

    private int marginCircleOutSite = 15; // khoảng cách giữa vòng tròng và các gạch xung quanh

    private int maxProgress = 100;

    private int normalColor = Color.LTGRAY;

    private int loadedColor = Color.RED;

    private int dashColor = Color.LTGRAY;

    private int textColor = Color.LTGRAY;

    private Circle normalCircle;

    private Circle loadedCircle;

    private Dash dash;

    private Text progressText;

    private ArrayList<PointF> pointDash1 = new ArrayList<>();

    private ArrayList<PointF> pointDash2 = new ArrayList<>();

    public ProgressBar(Context context) {
        super(context);
    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        normalCircle = new Circle();
        loadedCircle = new Circle();

        normalCircle.setStrokeWidth(strokeWidth);
        normalCircle.setColor(normalColor);

        loadedCircle.setStrokeWidth(strokeWidth);
        loadedCircle.setColor(loadedColor);

    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (getHeight() >= getWidth()) {
            circleRadius = (getWidth() / 2) - strokeWidth - dashLength - marginCircleOutSite;
        } else {
            circleRadius = (getHeight() / 2) - strokeWidth - dashLength - marginCircleOutSite;
        }

        pointCenter = new PointF(getWidth() / 2, getHeight() / 2);
        normalCircle.setCenterAndRadius(pointCenter.x, pointCenter.y, circleRadius);
        loadedCircle.setCenterAndRadius(pointCenter.x, pointCenter.y, circleRadius);
        progressText = new Text(pointCenter);
        progressText.setColor(textColor);
        initDash();
    }

    private void initDash() {
        pointDash1.clear();
        pointDash2.clear();

        for (int i = 0; i < 24; i++) {
            pointDash1.add(getPointDash(circleRadius + marginCircleOutSite + dashLength, i * 15));
            pointDash2.add(getPointDash(circleRadius + marginCircleOutSite, i * 15));
        }

        dash = new Dash();
        dash.setColor(dashColor);
        dash.setStrokeWidth(5);
    }

    private PointF getPointDash(float radius, int angle) {
        double x = pointCenter.x + (radius * Math.cos(Math.toRadians(angle)));
        double y = pointCenter.y + (radius * Math.sin(Math.toRadians(angle)));
        return new PointF((float) x, (float) y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredHeight = 80;

        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        } else {
            width = 0;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < pointDash1.size(); i++) {
            dash.draw(canvas, pointDash1.get(i), pointDash2.get(i));
        }
        normalCircle.draw(canvas, 360f);
        loadedCircle.draw(canvas, currentAngle);
        progressText.draw(canvas, currentProgress + "%");
    }


    public void setProgress(int progress) {
        if (progress > maxProgress) return;

        this.currentProgress = progress;
        currentAngle = (progress * 1f / maxProgress * 1f) * 360f;
        invalidate();
    }

    public void setMaxProgress(int max) {
        this.maxProgress = max;
        currentAngle = (currentAngle * 1f / maxProgress * 1f) * 360f;
        invalidate();
    }

    private class Circle {
        private Paint paint;

        private PointF center;

        private RectF bound;

        public Circle() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            center = new PointF();
        }


        public void setColor(int color) {
            paint.setColor(color);
        }

        public void setCenterAndRadius(float x, float y, float radius) {
            center.set(x, y);
            bound = new RectF(x - radius, y - radius, x + radius, y + radius);
        }

        public void setStrokeWidth(float width) {
            paint.setStrokeWidth(width);
        }

        public void draw(Canvas canvas, float sweepAngle) {
            canvas.drawArc(bound, 270f, sweepAngle, false, paint);
        }
    }

    private class Dash {
        private Paint paint;

        public Dash() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }


        public void setColor(int color) {
            paint.setColor(color);
        }

        public void setStrokeWidth(float width) {
            paint.setStrokeWidth(width);
        }

        public void draw(Canvas canvas, PointF p1, PointF p2) {
            canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
        }
    }

    private class Text {
        private Paint paint;

        private PointF center;

        public Text(PointF center) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(80);
            this.center = center;
        }


        public void setColor(int color) {
            paint.setColor(color);
        }

        public void draw(Canvas canvas, String text) {
            canvas.drawText(text, center.x, center.y - ((paint.descent() + paint.ascent()) / 2), paint);
        }
    }
}
