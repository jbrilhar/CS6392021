package pace.cs639.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageholder;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void downloadImage(View view) {
        textView = (TextView) findViewById(R.id.tv_loading);
        imageholder = (ImageView) findViewById(R.id.myRemoteImage);
        new ImageDownloader(imageholder,textView, bitmap)
                .execute("https://user-images.githubusercontent.com/60992141/123027463-e3d22300-d3ab-11eb-8960-63df3c19ba5f.jpg");
    }
}