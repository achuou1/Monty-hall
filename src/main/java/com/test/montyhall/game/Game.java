package com.test.montyhall.game;

import com.test.montyhall.enums.GameStrategy;
import com.test.montyhall.enums.Prize;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Game {

  private static final String ERROR_MESSAGE_NO_OPENED_BOXES_FOUND = "No unopened boxes found";
  private GameResult gameResultWithStay;
  private GameResult gameResultWithChange;

  private final List<Box> boxes = new ArrayList<>(3);

  private void initializeBoxes() {
    boxes.clear();
    boxes.add(Box.builder().prize(Prize.MONEY).build());
    boxes.add(Box.builder().prize(Prize.EMPTY).build());
    boxes.add(Box.builder().prize(Prize.EMPTY).build());
    Collections.shuffle(boxes);
    printBoxes();
  }

  public void runGame(GameStrategy gameStrategy, int numberOfIterations, Player player) {
    switch (gameStrategy) {
      case CHANGE:
        log.info("*************************** Running game with Change strategy **************************************");
        runGameWithChangeStrategy(numberOfIterations, player);
        break;
      case STAY:
        log.info("*************************** Running game with Stay strategy **************************************");
        runGameWithStayStrategy(numberOfIterations, player);
        break;
      default:
        log.info("Not possible to choice this strategy");
    }
  }

  private void runGameWithChangeStrategy(int numberOfIterations, Player player) {

    gameResultWithChange = new GameResult(GameStrategy.CHANGE);
    IntStream.range(0, numberOfIterations).forEach(value -> {
      initializeBoxes();
      int playerSelectedBox = playerChoiceBox(player);
      int emptyBox = openEmptyBox(playerSelectedBox);
      Box box = openBoxOtherThanGivenIndexes(playerSelectedBox, emptyBox);
      calculateResult(box, gameResultWithChange);
    });
  }

  private void runGameWithStayStrategy(int numberOfIterations, Player player) {
    gameResultWithStay = new GameResult(GameStrategy.STAY);
    IntStream.range(0, numberOfIterations)
        .forEach(value -> {
          initializeBoxes();
          int playerSelectedBox = player.choiceBox();
          // no need to open the empty box since we will always open the what player have selected
          Box box = openBox(playerSelectedBox);
          calculateResult(box, gameResultWithStay);
        });
  }

  private void calculateResult(Box box, GameResult gameResult) {
    if (box.getPrize().equals(Prize.MONEY)) {
      gameResult.incrementWin();
    } else {
      gameResult.incrementLose();
    }
  }

  private Box openBoxOtherThanGivenIndexes(int playerSelectedBoxIndex, int emptyBoxIndex) {
    List<Box> tempBoxes = new ArrayList<>(boxes);
    tempBoxes.set(playerSelectedBoxIndex, new Box(Prize.OPENED));
    tempBoxes.set(emptyBoxIndex, new Box(Prize.OPENED));

    Integer unOpenedBoxIndex = IntStream.range(0, tempBoxes.size())
        .filter(index -> !tempBoxes.get(index).getPrize().equals(Prize.OPENED))
        .boxed()
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE_NO_OPENED_BOXES_FOUND));

    return boxes.get(unOpenedBoxIndex);
  }

  private int openEmptyBox(int playerSelectedBox) {
    List<Integer> emptyBoxesIndexes = IntStream.range(0, boxes.size())
        .filter(index -> boxes.get(index).getPrize().equals(Prize.EMPTY) && index != playerSelectedBox)
        .boxed()
        .collect(Collectors.toList());
    return emptyBoxesIndexes.get(new Random().nextInt(emptyBoxesIndexes.size()));
  }

  private Box openBox(int playerSelectedBoxIndex) {
    return boxes.get(playerSelectedBoxIndex);
  }

  private int playerChoiceBox(Player player) {
    return player.choiceBox();
  }

  public void printGameResultWithStay() {
    log.info(gameResultWithStay.toString());
  }

  public void printGameResultWithChange() {
    log.info(gameResultWithChange.toString());
  }

  private void printBoxes() {
    log.info("*".repeat(100));
    IntStream.range(0, boxes.size())
        .forEach(index -> log.info("Box {} contains {}", index, boxes.get(index)));
  }
}
