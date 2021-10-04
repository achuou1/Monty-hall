package com.test.montyhall.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.test.montyhall.enums.GameStrategy;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void testCreatingObjectIsSuccessful() {

    Game game = new Game();
    assertNotNull(game);
  }

  @Test
  void testObjectCreatedSuccessfullyWhenCreatingGameResult() {

    Game game = new Game();
    Player player = new Player();
    game.runGame(GameStrategy.STAY, 50, player);

    assertEquals(3, game.getBoxes().size());
    assertTrue(game.getGameResultWithStay().getLose().get() > game.getGameResultWithStay().getWin().get());
  }

  @Test
  void testRunningGameWithChangeStrategyHaveMoreWinsThanLoses() {

    Game game = new Game();
    Player player = new Player();
    game.runGame(GameStrategy.CHANGE, 50, player);

    assertEquals(3, game.getBoxes().size());
    assertTrue(game.getGameResultWithChange().getLose().get() < game.getGameResultWithChange().getWin().get());
  }
}
