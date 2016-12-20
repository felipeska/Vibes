package com.hugeinc.vibes.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import com.hugeinc.vibes.Vibes;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
    ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
    ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
    ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
    ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);

    new Vibes.Builder().waves(4).start(40).time(700).color(Color.CYAN).into(imageView1);
    new Vibes.Builder().waves(10).time(1000).color(Color.RED).stroke(5).into(imageView2);
    new Vibes.Builder().waves(12).time(5000).color(Color.parseColor("#c3c3c3")).into(imageView3);
    new Vibes.Builder().into(imageView4);
    new Vibes.Builder().color(Color.BLUE).into(imageView5);
  }
}
