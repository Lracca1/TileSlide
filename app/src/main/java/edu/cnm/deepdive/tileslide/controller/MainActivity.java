package edu.cnm.deepdive.tileslide.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.tileslide.R;
import edu.cnm.deepdive.tileslide.model.Frame;
import edu.cnm.deepdive.tileslide.view.FrameAdapter;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private static int PUZZLE_SIZE = 4;

  private Frame frame;
  private FrameAdapter adapter;
  private GridView tileGrid;
  private MenuItem startItem;
  private MenuItem resetItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tileGrid = findViewById(R.id.tile_grid);
    tileGrid.setNumColumns(PUZZLE_SIZE);
    tileGrid.setOnItemClickListener(this);
    createPuzzle();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.options, menu);
    startItem = menu.findItem(R.id.action_start);
    resetItem = menu.findItem(R.id.action_reset);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_start:
        start();
        break;
      case R.id.action_reset:
        reset();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  /**
   * resets number of moves back to zero.
   */
  public void reset() {
    frame.reset();
  }

  /**
   * starts a new game
   */
  public void start() {
    createPuzzle();
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    // TODO Implement tile sliding by responding to clicks, and invoking any
    // relevant methods in the Frame class.
    if (frame.move(position / PUZZLE_SIZE, position % PUZZLE_SIZE)) {
      adapter.notifyDataSetChanged();
    } else {
      Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
    }
  }
  private void createPuzzle() {
    frame = new Frame(PUZZLE_SIZE, new Random());
    adapter = new FrameAdapter(this, frame);
    tileGrid.setAdapter(adapter);
  }
}
