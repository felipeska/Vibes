package com.hugeinc.vibes;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

public class Vibes extends BitmapDrawable {

  private final int width;
  private final int height;
  private final int waves;
  private final int stroke;
  private final int color;
  private final int time;
  private final int start;

  private Paint paint;
  private int radius;
  private int ratio;

  private Vibes(final int width, final int height, final int waves, final int stroke,
      final int color, final int time, final int start) {
    this.width = width;
    this.height = height;
    this.waves = waves;
    this.stroke = stroke;
    this.color = color;
    this.time = time;
    this.start = start;

    paint = buildPaint();
    radius = Math.min(width, height) / 2;
    buildAnimator().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        ratio = (int) animation.getAnimatedValue();
        invalidateSelf();
      }
    });
  }

  @Override public int getOpacity() {
    return PixelFormat.TRANSLUCENT;
  }

  @Override protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
  }

  @Override public void draw(Canvas canvas) {
    int count = canvas.save();
    for (int i = 0; i < waves; i++) {
      int currentRadius = (i * (radius - start) / waves) + ratio + start;
      int alpha = alpha(currentRadius);
      drawVibe(canvas, alpha, currentRadius);
    }
    canvas.restoreToCount(count);
  }

  private void drawVibe(Canvas canvas, int alpha, int currentRadius) {
    paint.setAlpha(alpha);
    int x = width / 2;
    int y = height / 2;
    canvas.drawCircle(x, y, currentRadius, paint);
  }

  private ValueAnimator buildAnimator() {
    ValueAnimator animator = ValueAnimator.ofInt(start / waves, radius / waves);
    animator.setRepeatCount(ValueAnimator.INFINITE);
    animator.setInterpolator(new LinearInterpolator());
    animator.setDuration(time);
    animator.start();
    return animator;
  }

  private Paint buildPaint() {
    Paint paint = new Paint();
    paint.setStrokeWidth(stroke);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(color);
    paint.setAntiAlias(true);
    return paint;
  }

  private int alpha(int currentRadius) {
    int constant = (currentRadius * 255 / radius);
    int calculated = 245 - constant;
    return Math.max(calculated, 0);
  }

  public static class Builder {
    private int waves = 12;
    private int time = 400;
    private int color = Color.BLACK;
    private int stroke = 3;
    private int start;

    public Builder waves(int waves) {
      this.waves = waves;
      return this;
    }

    public Builder time(int time) {
      this.time = time;
      return this;
    }

    public Builder color(int color) {
      this.color = color;
      return this;
    }

    public Builder stroke(int stroke) {
      this.stroke = stroke;
      return this;
    }

    public Builder start(int start) {
      this.start = start;
      return this;
    }

    public BitmapDrawable get(int size) {
      return new Vibes(size, size, waves, stroke, color, time, start);
    }

    public void into(final View view) {
      view.getViewTreeObserver()
          .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
              view.setBackgroundDrawable(
                  new Vibes(view.getWidth(), view.getHeight(), waves, stroke, color, time, start));
            }
          });
    }
  }
}