package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JoinQueue extends AppCompatActivity {
    TextView location,type,start,end,joinnum;
    String txtlocation,txttype,txtstart,txtend;
    int num=17;
    Button join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_queue);
        location= findViewById(R.id.joinlocation);
        type= findViewById(R.id.jointype);
        start= findViewById(R.id.joinstart);
        end= findViewById(R.id.joinend);
        joinnum= findViewById(R.id.joinnum);
        join= findViewById(R.id.joinfuelsubmit);

        Intent intent = getIntent();

        String txtlocation = intent.getStringExtra("location");
        String txtType = intent.getStringExtra("Type");
        String txtStart = intent.getStringExtra("Start");
        String txtEnd = intent.getStringExtra("End");
        // display the string into textView
        location.setText("Location: "+txtlocation);
        type.setText("Fuel Type: "+txtType);
        start.setText("Queus Start Time: "+txtStart);
        end.setText("Queues Guesses End Time: "+txtEnd);

        String innum= Integer.toString(num);
        joinnum.setText(innum);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=num+1;
                String innum= Integer.toString(num);
                joinnum.setText(innum);
                Intent intent = new Intent(getApplicationContext(), JoinedQueueUser.class);
                intent.putExtra("availableinQueue", innum);
                startActivity(intent);

            }
        });
    }
}