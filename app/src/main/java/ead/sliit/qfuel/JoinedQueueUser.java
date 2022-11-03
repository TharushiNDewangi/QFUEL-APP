package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JoinedQueueUser extends AppCompatActivity {
    TextView available,res;
    Button afterExit,beforeExit;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_queue_user);

        available= findViewById(R.id.joinednum);
        afterExit= findViewById(R.id.afterExit);
        beforeExit= findViewById(R.id.beforeExit);
        res= findViewById(R.id.result);

        Intent intent = getIntent();

        String availableNum = intent.getStringExtra("availableinQueue");
        available.setText(availableNum);
        num = Integer.parseInt(availableNum);

        afterExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=num-1;
                String innum= Integer.toString(num);
                available.setText(innum);
                res.setText("SEE YOU LATER");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
//                Intent intent = new Intent(getApplicationContext(), JoinedQueueUser.class);
//                intent.putExtra("availableinQueue", innum);
//                startActivity(intent);

            }
        });
        beforeExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=num-1;
                String innum= Integer.toString(num);
                available.setText(innum);
                res.setText("You waited Queue in 4 hrs. Sorry For Your Time. Come AGIN");
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
//                Intent intent = new Intent(getApplicationContext(), JoinedQueueUser.class);
//                intent.putExtra("availableinQueue", innum);
//                startActivity(intent);

            }
        });
    }
}