package com.example.ethan.pokerjournal;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int gameId;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    List<Game> gameList;
    DatabaseHelper db;

    HistoryFragment hist;
    StatsFragment stats;
    BankFragment bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        gameList = db.getAllGames();
        //
        hist = new HistoryFragment();
        stats = new StatsFragment();
        bank = new BankFragment();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager upViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(hist, "History");
        adapter.addFragment(stats, "Stats");
        adapter.addFragment(bank, "Bank");
        viewPager.setAdapter(adapter);
    }

    public void onClickNewGame(View v) {
        Intent intent = new Intent(MainActivity.this, GameFormActivity.class);
        startActivity(intent);
    }

    public void onClickDepositWithdraw(View v) {
        Intent intent = new Intent(MainActivity.this, BankFormActivity.class);
        startActivity(intent);
    }

    public void onClickResetGame(View v) {
        db.clearGames();
        hist.displayGames();
        stats.displayStats();
    }

    public void onClickResetBank(View v) {
        db.clearBank();
        bank.displayBanks();
    }

    public void onClickTest(View v) {
        gameId = v.getId();
        Intent intent = new Intent(MainActivity.this, GameDetailActivity.class);
        startActivity(intent);
    }
}
