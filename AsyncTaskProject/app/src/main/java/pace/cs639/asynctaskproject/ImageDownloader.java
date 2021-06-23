package pace.cs639.asynctaskproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cscharff.
 */
//url:"https://user-images.githubusercontent.com/60992141/123027463-e3d22300-d3ab-11eb-8960-63df3c19ba5f.jpg"
public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {


    private ImageView myImage;
    private TextView tv_load;
    private Bitmap bit;


    public ImageDownloader(ImageView myimg, TextView loadview, Bitmap b) {
        tv_load = loadview;
        bit = b;
        myImage = myimg;


    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i("hello1", "hello1");
        publishProgress(1);
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Failed to connect");
            }
            InputStream is = con.getInputStream();
            publishProgress(0);
            bit = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
            is.close();

        } catch (Exception e) {
            Log.e("Image", "Failed to load image", e);
            Log.e("error", e.getMessage());
        }
        return bit;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if (values[0] == 1) {
            tv_load.setText("Loading...");
        } else {
            tv_load.setText("");
        }
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        Log.i("hello4", "hello4");

        Log.i("hello5", "hello5");
        if (myImage != null && img != null) {
            Log.i("hello6", "hello6");
            myImage.setVisibility(View.VISIBLE);
            myImage.setImageBitmap(img);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}