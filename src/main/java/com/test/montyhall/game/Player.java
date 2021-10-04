package com.test.montyhall.game;

import java.util.Random;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Player {

  private int selectedBox;

  public int choiceBox() {
    Random random = new Random();
    selectedBox = random.nextInt(3);
    log.info("Player has chosen box {}", selectedBox);
    return selectedBox;
  }
}
