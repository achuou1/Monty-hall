package com.test.montyhall.game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.montyhall.enums.GameStrategy;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

class GameResultTest {

  @Test
  void testCreatingObjectIsSuccessful() {

    GameResult gameResult = new GameResult(GameStrategy.STAY);

    assertNotNull(gameResult);

    assertEquals(GameStrategy.STAY, gameResult.getGameStrategy());
    assertEquals(0, gameResult.getWin().get());
    assertEquals(0, gameResult.getLose().get());
  }

  @Test
  void testSuccessWhenIncrementWinAndLoseCreatingGameResult() {

    GameResult gameResult = new GameResult(GameStrategy.STAY);

    assertEquals(GameStrategy.STAY, gameResult.getGameStrategy());
    gameResult.incrementWin();
    gameResult.incrementLose();
    assertEquals(1, gameResult.getWin().get());
    assertEquals(1, gameResult.getLose().get());
    assertThat(gameResult.toString(), StringContains.containsString("Game ended with strategy:"));
  }
}
