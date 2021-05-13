package br.edu.ifsp.arq.domos5_2021.meupocket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meupocket.R;
import br.edu.ifsp.arq.domos5_2021.meupocket.dao.SiteDAO;
import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;

public class MainActivity extends AppCompatActivity {

    private List<Site> mSites;
    private ListView mListView;
    private ArrayAdapter<Site> mSiteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSites = SiteDAO.getInstance().getSites();
        mSiteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSites);

        mListView = findViewById(R.id.list_sites);
        mListView.setAdapter(mSiteArrayAdapter);
        mListView.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(
                getApplicationContext(),
                mSites.get(i).getTitle() + " | " + mSites.get(i).getUrl(),
                Toast.LENGTH_SHORT).show()
        );
    }
}