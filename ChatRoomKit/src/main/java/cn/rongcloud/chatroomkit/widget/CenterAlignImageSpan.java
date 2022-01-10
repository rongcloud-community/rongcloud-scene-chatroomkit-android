package cn.rongcloud.chatroomkit.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

/**
 * Created by gyn on 2021/11/22
 */
public class CenterAlignImageSpan extends ImageSpan {

    public CenterAlignImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        canvas.save();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        float translationY = (y + fm.descent + y + fm.ascent) / 2f - getDrawable().getBounds().bottom / 2f;
        canvas.translate(x, translationY);
        getDrawable().draw(canvas);
        canvas.restore();
    }
}
