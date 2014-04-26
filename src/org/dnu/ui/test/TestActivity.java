package org.dnu.ui.test;

import org.dnu.ui.MovableBitmapView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.Volley;

import org.dnu.ui.test.R;

/**
 * The Class TestActivity.
 * @author ISSKJ
 */
public class TestActivity extends Activity {

    /** The Constant TAG. */
    private static final String TAG = "TestActivity";

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final MovableBitmapView iv1 = (MovableBitmapView)findViewById(R.id.image_1);
        final MovableBitmapView iv2 = (MovableBitmapView)findViewById(R.id.image_2);
        final MovableBitmapView iv3 = (MovableBitmapView)findViewById(R.id.image_3);
        final MovableBitmapView iv4 = (MovableBitmapView)findViewById(R.id.image_4);
        final MovableBitmapView iv5 = (MovableBitmapView)findViewById(R.id.image_5);
        final MovableBitmapView iv6 = (MovableBitmapView)findViewById(R.id.image_6);

        iv1.setImageResource(R.drawable.sample1);
        iv2.setImageResource(R.drawable.sample2);
        iv3.setImageResource(R.drawable.sample3);
        iv4.setImageResource(R.drawable.sample4);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        ImageLoader loader = new ImageLoader(queue, new BitmapLruCache());

        String url = "http://cinephilefix.files.wordpress.com/2010/11/mooon.jpg";

        loader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, "onError:"+error.toString());
            }

            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                if (response == null) {
                    Log.v(TAG, "onResponse null");
                    return;
                }
                if (response.getBitmap() == null) {
                    Log.v(TAG, "onResponse bitmap null");
                    return;
                }
                iv5.setBitmap(response.getBitmap());
                iv6.setBitmap(response.getBitmap());
            }
        });
    }

}
