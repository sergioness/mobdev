package com.example.android.inguletstour.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.inguletstour.R;

public class ExpandedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded);

        Bundle bundle = getIntent().getExtras();
        String header = bundle.getString(Intent.EXTRA_TITLE);
        String body = bundle.getString(Intent.EXTRA_TEXT);
        int imageResId = bundle.getInt("ICON",-1);

        TextView headerView = findViewById(R.id.header_text_exp);
        headerView.setText(header);
        TextView bodyTextView = findViewById(R.id.body_text_exp);
        bodyTextView.setText(body);
        ImageView imageView = findViewById(R.id.image_exp);
        ConstraintLayout.LayoutParams imageParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        if(imageResId == -1) {
            // As a ConstraintLayout is the root of views above, it is important to save chain of constraints
            // in order to do not have unexpected overlays or so
            // For this reason, I need to set visibility of my ImageView to INVINSIBLE
            // so that allows me to have image covered desired space within the layout
            imageView.setVisibility(View.INVISIBLE);
            // Set height of the ImageView equals to 0dp I want it to be present at the layout,
            // covering exactly 0dp but(!) saving previous chain of constraints:
            // StubView ~ ImageView ~ BodyTextView
            imageParams.height = 0;
            imageView.setLayoutParams(imageParams);
        } else{
            imageView.setImageResource(imageResId);
            imageView.setVisibility(View.VISIBLE);
            imageParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
            imageView.setLayoutParams(imageParams);
        }
    }
}
