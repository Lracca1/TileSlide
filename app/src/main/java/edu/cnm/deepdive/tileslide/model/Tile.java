package edu.cnm.deepdive.tileslide.model;

import android.graphics.Bitmap;

/**
 * Tile class that consist of the number of a tile.
 */
public class Tile {

  private int number;

  /**
   *
   * @param number indicates the number of a tile.
   */
  public Tile(int number) {
    this.number = number;
  }

  /**
   *
   * @return returns the number of a tile.
   */
  public int getNumber() {
    return number;
  }

}
