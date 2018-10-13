package tw.sean.listtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private LinkedList<HashMap<String, Object>> data;
    String from[] = {"title", "cont", "img"};//宣告同時初始化的寫法
    int[] to = {R.id.item_title, R.id.item_content, R.id.item_img};

    private int[] imgs = {R.drawable.img0, R.drawable.img1, R.drawable.img2,
            R.drawable.img3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initListView();

    }
    //別把onCreate的內容寫太複雜，分開寫在這
    private void initListView(){
//        String from[] = {"title", "cont", "img"};//宣告同時初始化的寫法
//        int[] to = {R.id.item_title, R.id.item_content, R.id.item_img};
        data = new LinkedList<>();

        // V1
        //資料0
        //HashMap<String, String> r0 = new HashMap<>();
        //r0.put(from[0], "Value1");
        //data.add(r0);
        //資料1
        //HashMap<String, String> r1 = new HashMap<>();
        //r0.put(from[0], "Value2");
        //data.add(r1);

        // V2
        for (int i=0; i<100; i++){
            HashMap<String, Object> row = new HashMap<>();
            row.put(from[0],"title" + i);
            row.put(from[1],"content" +i);
            row.put(from[2], imgs[i%4]);
            data.add(row);
        }

        simpleAdapter = new SimpleAdapter(
               this, data, R.layout.item, from, to);

        //由資料形成版面(如照片app)(從data拋到adapter再丟到ListView)
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //HashMap<String, Object> target = data.get(position);

                Log.d("brad","item:1");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("brad","item:2");
                return false;//先測false再測true的手機長按反映Log
            }
        });
    }

    public void addItem(View view){
        HashMap<String, Object> row = new HashMap<>();
        row.put(from[0],"new");
        row.put(from[1],"content");
        row.put(from[2], imgs[(int)Math.random()*4]);
        data.add(0, row);
        simpleAdapter.notifyDataSetChanged();
    }

}
