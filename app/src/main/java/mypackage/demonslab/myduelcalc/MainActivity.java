package mypackage.demonslab.myduelcalc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.EventListener;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private ImageButton p1;
    private ImageButton p2;
    private TextView out1;
    private TextView out2;
    private ArrayList<ImageButton> inputs = new ArrayList<ImageButton>();
    private ArrayList<ImageButton> commands = new ArrayList<ImageButton>();
    private ArrayList<ImageButton> zeroes = new ArrayList<ImageButton>();
    private ImageButton equals;
    private ImageButton reset;

    private Integer start = 8000;
    private Integer curStart;
    private boolean player1 = true;
    private boolean calculating = false;
    private boolean add=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = (ImageButton) findViewById(R.id.btn_left);
        p2 = (ImageButton) findViewById(R.id.btn_right);
        out1 = (TextView) findViewById(R.id.output_p1);
        out2 = (TextView) findViewById(R.id.output_p2);
        equals = (ImageButton) findViewById(R.id.btn_equals);
        reset = (ImageButton) findViewById(R.id.btn_reset);

        commands.add((ImageButton)findViewById(R.id.btn_plus));
        commands.add((ImageButton)findViewById(R.id.btn_minus));

        inputs.add((ImageButton)findViewById(R.id.btn_1));
        inputs.add((ImageButton)findViewById(R.id.btn_2));
        inputs.add((ImageButton)findViewById(R.id.btn_3));
        inputs.add((ImageButton)findViewById(R.id.btn_4));
        inputs.add((ImageButton)findViewById(R.id.btn_5));
        inputs.add((ImageButton)findViewById(R.id.btn_6));
        inputs.add((ImageButton)findViewById(R.id.btn_7));
        inputs.add((ImageButton)findViewById(R.id.btn_8));
        inputs.add((ImageButton)findViewById(R.id.btn_9));

        zeroes.add((ImageButton)findViewById(R.id.btn_0));
        zeroes.add((ImageButton)findViewById(R.id.btn_00));
        zeroes.add((ImageButton)findViewById(R.id.btn_000));

        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        equals.setOnClickListener(this);
        reset.setOnClickListener(this);
        for (int i=0; i<inputs.size(); i++){
            inputs.get(i).setOnClickListener(this);
        }
        for (int i=0; i<commands.size(); i++){
            commands.get(i).setOnClickListener(this);
        }
        for (int i=0; i<zeroes.size(); i++){
            zeroes.get(i).setOnClickListener(this);
        }

        out1.setText(start.toString());
        out2.setText(start.toString());
        p1.setEnabled(false);
        commands.get(1).setEnabled(false);
    }

    @Override
    public void onClick(View v){
        //p1
        if (v.getId() == p1.getId()){
            if (!calculating)
                setState(p1);
        }
        //p2
        if (v.getId() == p2.getId()){
            if (!calculating)
                setState(p2);
        }
        //reset
        if (v.getId() == reset.getId()){
            calculating = false;
            out1.setText(start.toString());
            out2.setText(start.toString());
        }
        //plus
        if (v.getId() == commands.get(0).getId()){
            add=true;
            math();
        }
        //minus
        if (v.getId() == commands.get(1).getId()){
            add=false;
            math();
        }
        //zeroes
        for (int i=0; i<zeroes.size(); i++){
            if (v.getId() == zeroes.get(i).getId()){
                if (calculating == true){
                    Integer currentVal = Integer.parseInt(this.currentField().getText().toString());
                    String num = "1" + zeroes.get(i).getContentDescription();
                    currentVal*= Integer.parseInt(num);
                    this.currentField().setText(formatNum(currentVal));
                }
            }
        }
        //equals
        if (v.getId() == equals.getId()){
            if (calculating == true){
                calculating = false;
                Integer val;
                if (add){
                    val = curStart+Integer.parseInt(this.currentField().getText().toString().toString());
                    this.currentField().setText(formatNum(val));
                }
                else{
                    val = curStart-Integer.parseInt(this.currentField().getText().toString());
                    this.currentField().setText(formatNum(val));
                }
            }
        }
        //numbers
        for (int i=0; i<inputs.size(); i++){
            if (v.getId() == inputs.get(i).getId()){
                if (calculating == false){
                    curStart = Integer.parseInt(this.currentField().getText().toString());
                    calculating = true;
                    this.currentField().setText(inputs.get(i).getContentDescription());
                }
                else {
                    Integer val=Integer.parseInt(this.currentField().getText().toString());
                    val=(val*10)+Integer.parseInt((String)inputs.get(i).getContentDescription());
                    this.currentField().setText(formatNum(val));
                }
            }
        }
    }
    private TextView currentField(){
        return player1?out1:out2;
    }

    private void setState(ImageButton b){
        player1 = (b==p1)?true:false;
        if (player1){
            p1.setEnabled(false);
            p2.setEnabled(true);
        }
        else{
            p1.setEnabled(true);
            p2.setEnabled(false);
        }
    }

    private void math(){
        if (add){
            commands.get(0).setEnabled(false);
            commands.get(1).setEnabled(true);
        }
        else{
            commands.get(0).setEnabled(true);
            commands.get(1).setEnabled(false);
        }
    }
    private String formatNum(Integer s){
        if (s>=100000)
            s=100000;
        if (s<0)
            s=0;
        return s.toString();
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
