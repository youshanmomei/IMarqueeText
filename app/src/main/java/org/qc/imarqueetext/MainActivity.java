package org.qc.imarqueetext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.qc.imarqueetext.view.MarqueeText;

public class MainActivity extends AppCompatActivity {

    private MarqueeText mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt = (MarqueeText) findViewById(R.id.mt);
        mt.setText("从容地让自己活得更深刻些，怀英，慢慢来");
        mt.startScroll();

    }
}
