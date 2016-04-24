package cn.edu.hebiace.javajpk.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.hebiace.javajpk.R;

public class DetailActivity extends AppCompatActivity {

    private  String docpath;
    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        docpath = getIntent().getStringExtra(getString(R.string.docpath));
        tvContent = (TextView) findViewById(R.id.activity_detail_text);
        tvContent.setText(docpath);
    }
}
