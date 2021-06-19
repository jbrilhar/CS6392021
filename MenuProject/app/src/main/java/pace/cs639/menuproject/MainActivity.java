package pace.cs639.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if(id == R.id.action_settings){
           Toast t = Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
           t.show();
           return true;
       }
        else if(id == R.id.action_help){
            Toast t = Toast.makeText(this, "Help", Toast.LENGTH_SHORT);
            t.show();
           Log.d(LOG_TAG, "Help Button clicked!");
           Intent intent = new Intent(this, HelpActivity.class);
           startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchSMS(View view) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode("5555555")));
        intent.putExtra("sms_body","Hi John Brilhart, this message will not get sent because the number is fake!");
        startActivity(intent);
    }

    public void launchPhone(View view) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:2027621401"));
        startActivity(intent);
    }

    public void launchWeb(View view) {
        final Intent websiteintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.marines.com/"));
        startActivity(websiteintent);
    }

    public void launchMap(View view) {
        Uri geo = Uri.parse("geo:0,0?q=Columbia,Missouri");
        Intent geomap = new Intent(Intent.ACTION_VIEW, geo);
        startActivity(geomap);

    }

    public void launchShare(View view) {
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setText("Hi there!")
                .setType(mimeType)
                .setChooserTitle("Share the love: ")
                .startChooser();

    }

    public void launchNewActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
}
